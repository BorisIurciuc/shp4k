package train.shp4k.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import train.shp4k.domain.dto.CartDto;
import train.shp4k.domain.entity.Cart;
import train.shp4k.service.interfaces.CartService;

/**
 * 23/12/2024 shp4k
 *
 * @author Boris Iurciuc (cohort36)
 */

@RestController
@RequestMapping("/api/cart")
public class CartController {

  private final CartService service;
  public CartController(CartService service) {
    this.service = service;
  }

  @PostMapping
  public CartDto addCart(@RequestBody CartDto dto) {
    return service.addCart(dto);
  }

}
