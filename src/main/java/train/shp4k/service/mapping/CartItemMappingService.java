package train.shp4k.service.mapping;

import org.mapstruct.Mapper;
import train.shp4k.domain.dto.CartItemDto;
import train.shp4k.domain.entity.CartItem;

/**
* 24/12/2024
* shp4k
*
* @author Boris Iurciuc (cohort36)
*/
@Mapper(componentModel = "spring", uses = {ProductMappingService.class})
public interface CartItemMappingService {
  CartItem mapDtoToEntity(CartItemDto dto);
  CartItemDto mapEntityToDto(CartItem entity);
}

