package train.shp4k.exception_handling.exceptions;

/**
 * 22/12/2024 shp4k
 *
 * @author Boris Iurciuc (cohort36)
 */
public class ResourceAlreadyExistsException extends RuntimeException{
  public ResourceAlreadyExistsException(String message) {
    super(message);
  }
}
