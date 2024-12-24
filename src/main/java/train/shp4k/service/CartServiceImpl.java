package train.shp4k.service;

import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import train.shp4k.domain.dto.CartDto;
import train.shp4k.domain.entity.Cart;
import train.shp4k.domain.entity.CartItem;
import train.shp4k.domain.entity.Product;
import train.shp4k.domain.entity.User;
import train.shp4k.exception_handling.exceptions.ResourceNotFoundException;
import train.shp4k.repository.CartItemRepository;
import train.shp4k.repository.CartRepository;
import train.shp4k.repository.ProductRepository;
import train.shp4k.repository.UserRepository;
import train.shp4k.service.interfaces.CartService;
import train.shp4k.service.mapping.CartMappingService;
import train.shp4k.service.mapping.ProductMappingService;

@Service
public class CartServiceImpl implements CartService {

  private final CartRepository cartRepository;
  private final CartItemRepository cartItemRepository;
  private final ProductRepository productRepository;
  private final UserRepository userRepository;

  private final CartMappingService mappingService;
//  private final CartItemMappingService itemMappingService;
//  private final ProductMappingService productMappingService;

  public CartServiceImpl(CartRepository cartRepository, CartItemRepository cartItemRepository,
      ProductRepository productRepository, UserRepository userRepository,
      CartMappingService mappingService,
      ProductMappingService productMappingService) {
    this.cartRepository = cartRepository;
    this.cartItemRepository = cartItemRepository;
    this.productRepository = productRepository;
    this.userRepository = userRepository;
    this.mappingService = mappingService;
//    this.itemMappingService = itemMappingService;
//    this.productMappingService = productMappingService;
  }

  @Override
  @Transactional
  public CartDto addCart(Long userId, Long productId, Integer quantity) {
    if (quantity < 1) {
      throw new IllegalArgumentException("Quantity must be at least 1");
    }

    User user = userRepository.findById(userId)
        .orElseThrow(() -> new ResourceNotFoundException(
            "User not found with id: " + userId));

    Product product = productRepository.findById(productId)
        .orElseThrow(() -> new ResourceNotFoundException(
            "Product not found with id: " + productId));

    Cart cart = cartRepository.findByUserIdAndActiveTrue(userId)
        .orElseGet(() -> {
          Cart newCart = new Cart();
          newCart.setUser(user);
          newCart.setActive(true);
          return cartRepository.save(newCart);
        });

    Optional<CartItem> existingItem = cartItemRepository.findByCartIdAndProductId(cart.getId(), productId);
    if (existingItem.isPresent()) {
      CartItem item = existingItem.get();
      item.setQuantity(item.getQuantity() + quantity);
      cartItemRepository.save(item);
    } else {
      addItemToCart(cart, product, quantity);
    }

    return mappingService.mapEntityToDto(cart);
  }

  private void addItemToCart(Cart cart, Product product, Integer quantity) {
    CartItem cartItem = new CartItem();
    cartItem.setCart(cart);
    cartItem.setProduct(product);
    cartItem.setQuantity(quantity);
    cart.getCartItems().add(cartItem);

    cartItemRepository.save(cartItem);
  }

  @Override
  public List<CartDto> getAllCarts() {
    return List.of();
  }

  @Override
  public Optional<CartDto> getCart(Long id) {
    return null;
  }

  @Override
  public void removeCart(Long id) {
    Cart cart = cartRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("ResourceNotFoundException "));
    cart.setActive(false);
    cartRepository.save(cart);
  }

  @Override
  public CartDto updateCart(CartDto dto, Long id) {
    return null;
  }
}