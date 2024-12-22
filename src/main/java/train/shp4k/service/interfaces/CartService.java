package train.shp4k.service.interfaces;

import java.util.List;
import train.shp4k.domain.dto.CartDto;

public interface CartService {
  CartDto addCart(CartDto dto);
  List<CartDto> getAllCarts();
  CartDto getCart(Long id);
  CartDto removeCart(Long id);
  CartDto updateCart(CartDto dto, Long id);
}
