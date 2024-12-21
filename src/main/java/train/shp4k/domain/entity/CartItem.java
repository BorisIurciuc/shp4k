package train.shp4k.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.util.Objects;

/**
 * 21/12/2024 shp4k
 *
 * @author Boris Iurciuc (cohort36)
 */

@Entity
@Table(name = "cart_items")
public class CartItem{

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "cart_id", nullable = false)
  private Cart cart;

  @ManyToOne
  @JoinColumn(name = "product_id", nullable = false)
  private Product product;

  @Column(nullable = false)
  private int quantity;

  public CartItem(Long id, Cart cart, Product product, int quantity) {
    this.id = id;
    this.cart = cart;
    this.product = product;
    this.quantity = quantity;
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
        cartItem.product);
  }
  @Override
  public int hashCode() {
    return Objects.hash(id, cart, product, quantity);
  }

  @Override
  public String toString() {
    return "CartItem{" +
        "id=" + id +
        ", cart=" + cart +
        ", product=" + product +
        ", quantity=" + quantity +
        '}';
  }
}
