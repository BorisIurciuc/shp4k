package train.shp4k.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
  public CartDto openCart(
      @RequestParam Long userId,
      @RequestParam Long productId,
      @RequestParam Integer quantity) {
    return service.addCart(userId, productId, quantity);
  }

  @DeleteMapping
  public void closeCart(@RequestParam Long cartId) {
    service.removeCart(cartId);
  }
}


