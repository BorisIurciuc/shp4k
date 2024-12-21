package train.shp4k.exception_handling.exceptions;

/**
 * 16/12/2024 shp4k
 *
 * @author Boris Iurciuc (cohort36)
 */
public class ProductNotFoundException extends RuntimeException{
  public ProductNotFoundException(Long product_id){
    super(String.format("Product with id %d not found", product_id));
  }

}
