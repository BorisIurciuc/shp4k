package train.shp4k.service.mapping;

import org.mapstruct.*;
import train.shp4k.domain.dto.CartDto;
import train.shp4k.domain.entity.Cart;

@Mapper(
    componentModel = "spring",
    uses = {ProductMappingService.class, UserMappingService.class},
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
    unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface CartMappingService {

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "cartItems", source = "cartItems")
  @Mapping(target = "active", source = "active")
  @Mapping(target = "user.roles", ignore = true)
  @Mapping(target = "user.authorities", ignore = true)
  Cart mapDtoToEntity(CartDto dto);

  @Mapping(target = "cartItems", source = "cartItems")
  CartDto mapEntityToDto(Cart entity);
}



