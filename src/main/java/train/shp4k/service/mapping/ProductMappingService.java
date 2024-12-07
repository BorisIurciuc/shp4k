package train.shp4k.service.mapping;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import train.shp4k.domain.dto.ProductDto;
import train.shp4k.domain.entity.Product;

@Mapper(componentModel = "spring")
public interface ProductMappingService {

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "active", constant = "true")
  Product mapDtoToEntity(ProductDto dto);
  ProductDto mapEntityToDto(Product entity);

}
