package train.shp4k.controller;

import io.swagger.v3.oas.annotations.Operation;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import train.shp4k.domain.dto.CartDto;
import train.shp4k.domain.entity.Cart;
import train.shp4k.domain.entity.CartItem;
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
      @RequestBody CartItem cartItem) {

    return service.addCart(userId, productId, cartItem.getQuantity());
  }

  @GetMapping
  @Operation(summary = "Получить все корзины", description = "Receive all carts.")
  public List<CartDto> getCarts() {
    return service.getAllCarts();
  }

  @GetMapping("/{id}")
  @Operation(summary = "Получить корзины по ее id", description = "Receive carts by id")
  public ResponseEntity<CartDto> getCartById(@PathVariable Long id) {
    CartDto cartDto = service.getCartById(id);
    return ResponseEntity.ok(cartDto);
  }

  @DeleteMapping("/{cartId}")
  @Operation(summary = "Закрыть корзину", description = "Make cart not active ID")
  public ResponseEntity<Void> closeCart(@PathVariable Long cartId) {
    service.removeCart(cartId);
    return ResponseEntity.noContent().build();
  }

  @DeleteMapping("/{cartId}/items/{cartItemId}")
  @Operation(summary = "Удалить продукт из корзины", description = "Remove cartItem from cart")
  public ResponseEntity<Void> removeProductFromCart(
      @PathVariable Long cartId,
      @PathVariable Long cartItemId) {
    service.removeProductFromCart(cartId, cartItemId);
    return ResponseEntity.noContent().build();
  }

}


