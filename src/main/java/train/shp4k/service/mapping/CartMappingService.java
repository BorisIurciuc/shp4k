package train.shp4k.service.mapping;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import train.shp4k.domain.dto.CartDto;
import train.shp4k.domain.entity.Cart;

/**
 * 22/12/2024 shp4k
 *
 * @author Boris Iurciuc (cohort36)
 */

@Mapper(componentModel = "spring", uses = {CartItemMappingService.class})
public interface CartMappingService {

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "cartItems", source = "cartItems") // Ensure nested mapping
  Cart mapDtoToEntity(CartDto dto);

  @Mapping(target = "cartItems", source = "cartItems") // Ensure nested mapping
  CartDto mapEntityToDto(Cart entity);

}
