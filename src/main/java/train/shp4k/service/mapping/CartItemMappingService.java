package train.shp4k.service.mapping;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import train.shp4k.domain.dto.CartItemDto;
import train.shp4k.domain.entity.CartItem;

@Mapper(componentModel = "spring")
public interface CartItemMappingService {

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "createdAt", ignore = true)
  CartItem mapDtoToEntity(CartItemDto dto);
  CartItemDto mapEntityToDto(CartItem entity);

}
