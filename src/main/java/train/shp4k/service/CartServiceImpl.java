package train.shp4k.service;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import train.shp4k.domain.dto.CartDto;
import train.shp4k.domain.entity.Cart;
import train.shp4k.domain.entity.CartItem;
import train.shp4k.domain.entity.Product;
import train.shp4k.domain.entity.User;
import train.shp4k.exception_handling.exceptions.ResourceAlreadyExistsException;
import train.shp4k.exception_handling.exceptions.ResourceNotFoundException;
import train.shp4k.repository.CartItemRepository;
import train.shp4k.repository.CartRepository;
import train.shp4k.repository.ProductRepository;
import train.shp4k.repository.UserRepository;
import train.shp4k.service.interfaces.CartService;
import train.shp4k.service.mapping.CartItemMappingService;
import train.shp4k.service.mapping.CartMappingService;
import train.shp4k.service.mapping.ProductMappingService;
import train.shp4k.service.mapping.UserMappingService;

/**
 * 22/12/2024 shp4k
 *
 * @author Boris Iurciuc (cohort36)
 */
@Service
public class CartServiceImpl implements CartService {

  private final CartRepository cartRepository;
  private final CartItemRepository cartItemRepository;
  private final ProductRepository productRepository;
  private final UserRepository userRepository;

  private final CartMappingService mappingService;
  private final CartItemMappingService itemMappingService;
  private final ProductMappingService productMappingService;
  private final UserMappingService userMappingService;

  public CartServiceImpl(CartRepository cartRepository, CartItemRepository cartItemRepository,
      ProductRepository productRepository, UserRepository userRepository,
      CartMappingService mappingService, CartItemMappingService itemMappingService,
      ProductMappingService productMappingService, UserMappingService userMappingService) {
    this.cartRepository = cartRepository;
    this.cartItemRepository = cartItemRepository;
    this.productRepository = productRepository;
    this.userRepository = userRepository;
    this.mappingService = mappingService;
    this.itemMappingService = itemMappingService;
    this.productMappingService = productMappingService;
    this.userMappingService = userMappingService;
  }

  @Override
  public CartDto addCart(Long userId, Long productId, Integer quantity) {
    // Find user
    User user = userRepository.findById(userId)
        .orElseThrow(() -> new ResourceNotFoundException(
            "User not found with id: " + userId));

    // Find product
    Product product = productRepository.findById(productId)
        .orElseThrow(() -> new ResourceNotFoundException(
            "Product not found with id: " + productId));

    // Check if user already has an active cart
    Optional<Cart> existingCart = cartRepository.findActiveCartByUserId(userId);
    if (existingCart.isPresent()) {
      throw new ResourceAlreadyExistsException(
          "Active cart already exists for user: " + userId);
    }

    // Create new cart
    Cart cart = new Cart();
    cart.setUser(user);
    cart.setActive(true);
    Cart savedCart = cartRepository.save(cart);

    // Create cart item
    CartItem cartItem = new CartItem();
    cartItem.setCart(savedCart);
    cartItem.setProduct(product);
    cartItem.setQuantity(quantity);
    cartItemRepository.save(cartItem);

    // Retrieve updated cart with all relationships
    Cart resultCart = cartRepository.findById(savedCart.getId())
        .orElseThrow(() -> new ResourceNotFoundException(
            "Cart not found after saving with id: " + savedCart.getId()));

    // Convert to DTO and return
    return mappingService.mapEntityToDto(resultCart);
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
  public Optional<CartDto> removeCart(Long id) {
    return null;
  }

  @Override
  public CartDto updateCart(CartDto dto, Long id) {
    return null;
  }
}
