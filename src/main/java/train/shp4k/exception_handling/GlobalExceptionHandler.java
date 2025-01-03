package train.shp4k.exception_handling;

import train.shp4k.exception_handling.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import train.shp4k.exception_handling.exceptions.ProductNotFoundException;
import train.shp4k.exception_handling.exceptions.ResourceAlreadyExistsException;
import train.shp4k.exception_handling.exceptions.ResourceNotFoundException;

/**
 * 16/12/2024 shp4k
 *
 * @author Boris Iurciuc (cohort36)
 */

@RestControllerAdvice
public class GlobalExceptionHandler {

  private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

  @ExceptionHandler(ProductNotFoundException.class)
  public ResponseEntity<Response> handleProductNotFoundException(ProductNotFoundException e) {
    log.error(e.getMessage());
    Response response = new Response(e.getMessage(), HttpStatus.NOT_FOUND.value());
    return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(ResourceNotFoundException.class)
  public ResponseEntity<Response> handleResourceNotFoundException(ResourceNotFoundException e) {
    log.error(e.getMessage());
    Response response = new Response(e.getMessage(), HttpStatus.NOT_FOUND.value());
    return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(ResourceAlreadyExistsException.class)
  public ResponseEntity<Response> handleResourceAlreadyExistsException(ResourceAlreadyExistsException e) {
    log.error(e.getMessage());
    Response response = new Response(e.getMessage(), HttpStatus.CONFLICT.value());
    return new ResponseEntity<>(response, HttpStatus.CONFLICT);
  }
}
