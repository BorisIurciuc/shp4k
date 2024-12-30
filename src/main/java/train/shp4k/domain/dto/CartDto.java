package train.shp4k.domain.dto;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import train.shp4k.domain.entity.CartItem;

/**
 * 20/12/2024 shp4k
 *
 * @author Boris Iurciuc (cohort36)
 */

public class CartDto {

  private Long id;

  @NotNull(message = "User cannot be null")
  private UserDto user;

  private List<CartItem> cartItems = new ArrayList<>();

  @NotNull(message = "Total price cannot be null")
  @Min(value = 0, message = "Total price cannot be negative")
  private BigDecimal totalPrice;

  @Min(value = 0, message = "Total items cannot be negative")
  private int totalItems;

  private boolean active;

  public CartDto() {}

  public CartDto(Long id, UserDto user, List<CartItem> cartItems, BigDecimal totalPrice,
      int totalItems, boolean active) {
    this.id = id;
    this.user = user;
    this.cartItems = cartItems;
    this.totalPrice = totalPrice;
    this.totalItems = totalItems;
    this.active = active;
  }

  public Long getId() {    return id;  }
  public void setId(Long id) {    this.id = id;  }
  public @NotNull(message = "User cannot be null") UserDto getUser() {    return user;  }
  public void setUser(      @NotNull(message = "User cannot be null") UserDto user) {
    this.user = user;
  }
  public List<CartItem> getCartItems() {    return cartItems;  }
  public void setCartItems(List<CartItem> cartItems) {    this.cartItems = cartItems;  }
  public @NotNull(message = "Total price cannot be null") @Min(value = 0, message = "Total price cannot be negative") BigDecimal getTotalPrice() {
    return totalPrice;
  }
  public void setTotalPrice(
      @NotNull(message = "Total price cannot be null") @Min(value = 0, message = "Total price cannot be negative") BigDecimal totalPrice) {
    this.totalPrice = totalPrice;
  }
  @Min(value = 0, message = "Total items cannot be negative")
  public int getTotalItems() {
    return totalItems;
  }
  public void setTotalItems(
      @Min(value = 0, message = "Total items cannot be negative") int totalItems) {
    this.totalItems = totalItems;
  }

  public boolean isActive() {
    return active;
  }

  public void setActive(boolean active) {
    this.active = active;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof CartDto cartDto)) {
      return false;
    }
    return totalItems == cartDto.totalItems && Objects.equals(id, cartDto.id)
        && Objects.equals(user, cartDto.user) && Objects.equals(cartItems,
        cartDto.cartItems) && Objects.equals(totalPrice, cartDto.totalPrice);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, user, cartItems, totalPrice, totalItems);
  }

  @Override
  public String toString() {
    return "CartDto{" + "id=" + id + ", user=" + user + ", cartItems=" + cartItems +
        ", totalPrice=" + totalPrice + ", totalItems=" + totalItems +
        '}';
  }
}
