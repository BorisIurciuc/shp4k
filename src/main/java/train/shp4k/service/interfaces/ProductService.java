package train.shp4k.service.interfaces;

import java.util.List;
import train.shp4k.domain.dto.ProductDto;

public interface ProductService {

  ProductDto addProduct(ProductDto dto);
  List<ProductDto> getProductsAll();
  ProductDto getProductById(Long id);
  ProductDto updateProduct(Long id, ProductDto dto);
  void deleteProductById(Long id);
}
