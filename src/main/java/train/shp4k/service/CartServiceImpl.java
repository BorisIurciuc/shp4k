package train.shp4k.service;

import java.util.List;
import org.springframework.stereotype.Service;
import train.shp4k.domain.dto.CartDto;
import train.shp4k.repository.CartRepository;
import train.shp4k.service.interfaces.CartService;
import train.shp4k.service.mapping.CartItemMappingService;
import train.shp4k.service.mapping.CartMappingService;

/**
 * 22/12/2024 shp4k
 *
 * @author Boris Iurciuc (cohort36)
 */
@Service
public class CartServiceImpl implements CartService {

  private CartRepository repository;
  private CartMappingService mappingService;
  private CartItemMappingService itemMappingService;

  @Override
  public CartDto addCart(CartDto dto) {
    return null;
  }

  @Override
  public List<CartDto> getAllCarts() {
    return List.of();
  }

  @Override
  public CartDto getCart(Long id) {
    return null;
  }

  @Override
  public CartDto removeCart(Long id) {
    return null;
  }

  @Override
  public CartDto updateCart(CartDto dto, Long id) {
    return null;
  }
}
