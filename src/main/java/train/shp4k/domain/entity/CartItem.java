package train.shp4k.domain.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
//import jakarta.persistence.Temporal;
//import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Min;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * 21/12/2024 shp4k
 *
 * @author Boris Iurciuc (cohort36)
 */

@Entity
@Table(name = "cart_items", indexes = {
    @Index(name = "idx_cart_id", columnList = "cart_id"),
    @Index(name = "idx_cart_product_unique", columnList = "cart_id, product_id", unique = true),
})
public class CartItem{

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JsonBackReference
  @JoinColumn(name = "cart_id", nullable = false)
  private Cart cart;

  @ManyToOne
  @JoinColumn(name = "product_id", nullable = false)
  private Product product;

  @Min(value = 1, message = "Quantity must be at least 1")
  @Column(nullable = false)
  private int quantity;

  @Column(name = "created_at", nullable = false)
  //@Temporal(TemporalType.TIMESTAMP)
  private LocalDateTime createdAt;

  @PrePersist
  protected void onCreate() {
    createdAt = LocalDateTime.now();
  }

  public CartItem(Long id, Cart cart, Product product, int quantity) {
    this.id = id;
    this.cart = cart;
    this.product = product;
    this.quantity = quantity;
    this.createdAt = LocalDateTime.now();
  }

  public CartItem() {}

  public Long getId() {    return id;  }
  public void setId(Long id) {    this.id = id;  }
  public Cart getCart() {    return cart;  }
  public void setCart(Cart cart) {    this.cart = cart;  }
  public Product getProduct() {    return product;  }
  public void setProduct(Product product) {    this.product = product;  }
  public int getQuantity() {    return quantity;  }
  public void setQuantity(int quantity) {   this.quantity = quantity;  }
  public LocalDateTime getCreatedAt() {    return createdAt;  }
  public void setCreatedAt(LocalDateTime createdAt) {    this.createdAt = createdAt;  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof CartItem cartItem)) {
      return false;
    }
    return quantity == cartItem.quantity && Objects.equals(id, cartItem.id)
        && Objects.equals(cart, cartItem.cart) && Objects.equals(product,
        cartItem.product) &&
        Objects.equals(createdAt, cartItem.createdAt);
  }
  @Override
  public int hashCode() {
    return Objects.hash(id, cart, product, quantity, createdAt);
  }

  @Override
  public String toString() {
    return "CartItem{" +
        "id=" + id + ", cart=" + cart + ", product=" + product + ", quantity=" + quantity
        + ", createdAt=" + createdAt + '}';
  }
}
