package train.shp4k.service.interfaces;

import java.util.List;
import java.util.Optional;
import train.shp4k.domain.dto.CartDto;

public interface CartService {
  CartDto addCart(Long userId, Long productId, Integer quantity);
  List<CartDto> getAllCarts();
  CartDto getCartById(Long id);
  void removeCart(Long id);
  CartDto updateCart(CartDto dto, Long id);
}
