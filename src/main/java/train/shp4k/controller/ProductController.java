package train.shp4k.controller;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import train.shp4k.domain.dto.ProductDto;
import train.shp4k.service.interfaces.ProductService;

/**
 * 07/12/2024 shp4k
 *
 * @author Boris Iurciuc (cohort36)
 */

@RestController
@RequestMapping("/products")
public class ProductController {

  private ProductService service;

  public ProductController(ProductService service) {
    this.service = service;
  }

  @PostMapping
  public ProductDto addProduct(ProductDto dto) {
    return service.addProduct(dto);
  }

  @GetMapping
  public List<ProductDto> getProductsAll() {
    return service.getProductsAll();
  }

}
