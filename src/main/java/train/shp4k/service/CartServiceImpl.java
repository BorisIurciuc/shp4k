package train.shp4k.service;

import jakarta.transaction.Transactional;
import java.math.BigDecimal;
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
  private final UserRepository userRepository;
  private final ProductRepository productRepository;

  private final CartMappingService mappingService;
  private final CartItemRepository cartItemRepository;


  public CartServiceImpl(CartRepository cartRepository, UserRepository userRepository,
      ProductRepository productRepository, CartMappingService mappingService,
      CartItemRepository cartItemRepository) {
    this.cartRepository = cartRepository;
    this.userRepository = userRepository;
    this.productRepository = productRepository;
    this.mappingService = mappingService;
    this.cartItemRepository = cartItemRepository;
  }

  @Override
  @Transactional
  public CartDto addCart(Long userId, Long productId, Integer quantity) {
    // Проверяем, существует ли пользователь
    User user = userRepository.findById(userId)
        .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));

    // Проверяем, существует ли продукт
    Product product = productRepository.findById(productId)
        .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + productId));

    // Ищем активную корзину пользователя
    Optional<Cart> optionalCart = cartRepository.findByUserAndActive(user, true);

    Cart cart;
    if (optionalCart.isPresent()) {
      // Если активная корзина существует, используем её
      cart = optionalCart.get();
    } else {
      // Если активной корзины нет, создаем новую
      cart = new Cart();
      cart.setUser(user);
      cart.setActive(true);
      cart.setTotalPrice(BigDecimal.ZERO);
      cart.setTotalItems(0);
    }

    // Ищем, есть ли товар уже в корзине
    Optional<CartItem> existingCartItem = cart.getCartItems()
        .stream()
        .filter(cartItem -> cartItem.getProduct().getId().equals(productId))
        .findFirst();

    if (existingCartItem.isPresent()) {
      // Если товар уже в корзине, обновляем количество
      CartItem cartItem = existingCartItem.get();
      cartItem.setQuantity(cartItem.getQuantity() + quantity);
    } else {
      // Если товара нет, добавляем его в корзину
      CartItem newCartItem = new CartItem();
      newCartItem.setCart(cart);
      newCartItem.setProduct(product);
      newCartItem.setQuantity(quantity);
      cart.getCartItems().add(newCartItem);
    }

    // Пересчитываем общую стоимость и количество товаров в корзине
    updateCartTotals(cart);

    // Сохраняем корзину
    Cart savedCart = cartRepository.save(cart);

    // Возвращаем DTO
    return mappingService.mapEntityToDto(savedCart);
  }

  /**
   * Пересчитывает общую стоимость и количество товаров в корзине.
   */
  private void updateCartTotals(Cart cart) {
    int totalItems = cart.getCartItems()
        .stream()
        .mapToInt(CartItem::getQuantity)
        .sum();
    BigDecimal totalPrice = cart.getCartItems()
        .stream()
        .map(cartItem -> cartItem.getProduct().getPrice().multiply(BigDecimal.valueOf(cartItem.getQuantity())))
        .reduce(BigDecimal.ZERO, BigDecimal::add);

    cart.setTotalItems(totalItems);
    cart.setTotalPrice(totalPrice);
  }


  @Override
  public List<CartDto> getAllCarts() {
        return cartRepository.findAll()
            .stream()
            .map(mappingService::mapEntityToDto)
            .toList();
  }

  @Override
  public CartDto getCartById(Long id) {
    Cart entity = cartRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Cart not found with id: " + id));
    return mappingService.mapEntityToDto(entity);
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

  @Override
  public void removeProductFromCart(Long cartId, Long cartItemId) {
    // 1. Проверка существования корзины
    Cart cart = cartRepository.findById(cartId)
        .orElseThrow(() -> new ResourceNotFoundException("Cart not found with id: " + cartId));
    // 2. Поиск записи в таблице cart_items
    CartItem cartItem = cartItemRepository.findById(cartItemId)
        .orElseThrow(() -> new ResourceNotFoundException("CartItem not found with id: " + cartItemId));
    // 3. Убедимся, что cartItem принадлежит указанной корзине
    if (!cartItem.getCart().getId().equals(cartId)) {
      throw new RuntimeException("Cart item does not belong to the cart  with id: " + cartId);
    }
    // 4. Удаление записи
    cartItemRepository.delete(cartItem);
  }
}




