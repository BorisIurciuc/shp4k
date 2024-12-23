package train.shp4k.service.interfaces;

import java.util.List;
import java.util.Optional;
import train.shp4k.domain.dto.CartDto;

public interface CartService {
  CartDto addCart(CartDto dto);
  List<CartDto> getAllCarts();
  Optional<CartDto> getCart(Long id);
  Optional<CartDto> removeCart(Long id);
  CartDto updateCart(CartDto dto, Long id);
}
