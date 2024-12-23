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
  public CartDto addCart(CartDto dto) {
    // Проверяем существование пользователя
    User user = userRepository.findById(dto.getUser().getId())
        .orElseThrow(() -> new ResourceNotFoundException(
            "User not found with id: " + dto.getUser().getId()));

    // Проверяем существование всех продуктов в CartItems
    dto.getCartItems().forEach(cartItemDto -> {
      Product product = productRepository.findById(cartItemDto.getProduct().getId())
          .orElseThrow(() -> new ResourceNotFoundException(
              "Product not found with id: " + cartItemDto.getProduct().getId()));
    });

    // Проверяем, нет ли уже активной корзины у пользователя
    Optional<Cart> existingCart = cartRepository.findActiveCartByUserId(user.getId());
    if (existingCart.isPresent()) {
      throw new ResourceAlreadyExistsException(
          "Active cart already exists for user: " + user.getId());
    }

    // Конвертируем DTO в сущность
    Cart cartEntity = mappingService.mapDtoToEntity(dto);
    cartEntity.setUser(user);
    cartEntity.setActive(true); // Устанавливаем статус активной корзины

    // Сохраняем корзину
    Cart savedCart = cartRepository.save(cartEntity);

    // Добавляем каждый CartItem отдельно, связывая с сохраненной корзиной
    if (dto.getCartItems() != null && !dto.getCartItems().isEmpty()) {
      dto.getCartItems().forEach(cartItemDto -> {
        Product product = productRepository.findById(cartItemDto.getProduct().getId())
            .orElseThrow(() -> new ResourceNotFoundException(
                "Product not found with id: " + cartItemDto.getProduct().getId()));

        CartItem cartItem = itemMappingService.mapDtoToEntity(cartItemDto);
        cartItem.setCart(savedCart);
        cartItem.setProduct(product);
        cartItemRepository.save(cartItem);
      });
    }

    // Получаем обновленную корзину со всеми связями
    Cart resultCart = cartRepository.findById(savedCart.getId())
        .orElseThrow(() -> new ResourceNotFoundException(
            "Cart not found after saving with id: " + savedCart.getId()));

    // Конвертируем обратно в DTO и возвращаем
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
