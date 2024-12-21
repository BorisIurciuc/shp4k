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

  private final ProductRepository repository;
  private final ProductMappingService mappingService;

  public ProductServiceImpl(ProductRepository repository, ProductMappingService mappingService) {
    this.repository = repository;
    this.mappingService = mappingService;
  }

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
    Product entity = repository.findById(id)
        .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
    return mappingService.mapEntityToDto(entity);
  }

  @Override
  public ProductDto updateProduct(Long id, ProductDto dto) {
    Product existingProduct = repository.findById(id)
        .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));

    existingProduct.setTitle(dto.getTitle());
    existingProduct.setDescription(dto.getDescription());
    existingProduct.setPrice(dto.getPrice());
    existingProduct.setImage(dto.getImage());
    existingProduct.setStockQuantity(dto.getStockQuantity());


    Product updatedProduct = repository.save(existingProduct);
    return mappingService.mapEntityToDto(updatedProduct);
  }

  @Override
  public void deleteProductById(Long id) {
    repository.deleteById(id);
  }
}
