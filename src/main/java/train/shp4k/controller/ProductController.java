package train.shp4k.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
@RequestMapping("/api/products")
public class ProductController {

  private final ProductService service;

  public ProductController(ProductService service) {
    this.service = service;
  }

//  @PostMapping
//  @Operation(summary = "Add a new product", description = "Creates a new product and saves it in the database.")
//  public ResponseEntity<ProductDto> addProduct(@Valid @RequestBody ProductDto dto) {
//    ProductDto product = service.addProduct(dto);
//    return ResponseEntity.ok(product);
//  }

  @PostMapping
  public ProductDto save(@Valid @RequestBody ProductDto dto){
    return service.addProduct(dto);
  }

  @GetMapping
  @Operation(summary = "Получить все продукты", description = "Receive all products.")
  public List<ProductDto> getProductsAll() {
    return service.getProductsAll();
  }

  @GetMapping("/{id}")
  public ResponseEntity<ProductDto> getProductById(@PathVariable Long id) {
      ProductDto product = service.getProductById(id);
      return ResponseEntity.ok(product); // Статус 200 OK
  }

  @PutMapping("/{id}")
  public ResponseEntity<ProductDto> updateProduct(@PathVariable Long id, @RequestBody ProductDto dto) {
    ProductDto updatedDto  = service.updateProduct(id, dto);
    return ResponseEntity.ok(updatedDto);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
    service.deleteProductById(id);
    return ResponseEntity.noContent().build();
  }
}
