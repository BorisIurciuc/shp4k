package train.shp4k.service;

import java.util.List;
import org.springframework.stereotype.Service;
import train.shp4k.domain.dto.ProductDto;
import train.shp4k.domain.entity.Product;
import train.shp4k.repository.ProductRepository;
import train.shp4k.service.interfaces.ProductService;
import train.shp4k.service.mapping.ProductMappingService;

/**
 * 07/12/2024 shp4k
 *
 * @author Boris Iurciuc (cohort36)
 */

@Service
public class ProductServiceImpl implements ProductService {

  private ProductRepository repository;
  private ProductMappingService mappingService;

  @Override
  public ProductDto addProduct(ProductDto dto) {
    Product entity = mappingService.mapDtoToEntity(dto);
    repository.save(entity);
    return mappingService.mapEntityToDto(entity);
  }

  @Override
  public List<ProductDto> getProductsAll() {
    return repository.findAll()
        .stream()
        .map(mappingService::mapEntityToDto)
        .toList();
  }

  @Override
  public ProductDto getProductById(Long id) {
    return null;
  }

  @Override
  public ProductDto updateProduct(ProductDto dto) {
    return null;
  }

  @Override
  public void deleteProductById(ProductDto dto) {

  }
}
