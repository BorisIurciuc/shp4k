package train.shp4k.domain.dto;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * 21/12/2024 shp4k
 *
 * @author Boris Iurciuc (cohort36)
 */

public class CartItemDto {

  private Long id;

  private CartDto cart;

  private ProductDto product;

  private int quantity;

  private LocalDateTime createdAt;

  public CartItemDto(Long id, CartDto cart, ProductDto product, int quantity,
      LocalDateTime createdAt) {
    this.id = id;
    this.cart = cart;
    this.product = product;
    this.quantity = quantity;
    this.createdAt = createdAt;
  }

  public CartItemDto() {}

  public Long getId() {    return id;  }
  public void setId(Long id) {    this.id = id;  }
  public CartDto getCart() {    return cart;  }
  public void setCart(CartDto cart) {    this.cart = cart;  }
  public ProductDto getProduct() {    return product;  }
  public void setProduct(ProductDto product) {    this.product = product;  }
  public int getQuantity() {    return quantity;  }
  public void setQuantity(int quantity) {    this.quantity = quantity;  }
  public LocalDateTime getCreatedAt() {    return createdAt;  }
  public void setCreatedAt(LocalDateTime createdAt) {    this.createdAt = createdAt;  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof CartItemDto that)) {
      return false;
    }
    return getQuantity() == that.getQuantity() && Objects.equals(getId(), that.getId())
        && Objects.equals(getCart(), that.getCart()) && Objects.equals(
        getProduct(), that.getProduct()) && Objects.equals(getCreatedAt(),
        that.getCreatedAt());
  }
  @Override
  public int hashCode() {
    return Objects.hash(getId(), getCart(), getProduct(), getQuantity(), getCreatedAt());
  }

  @Override
  public String toString() {
    return "CartItemDto{" +  "id=" + id + ", cart=" + cart + ", product=" + product +
        ", quantity=" + quantity + ", createdAt=" + createdAt + '}';
  }
}
