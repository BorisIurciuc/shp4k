package train.shp4k.domain.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 20/12/2024 shp4k
 *
 * @author Boris Iurciuc (cohort36)
 */

@Entity
@Table(name = "cart")
public class Cart {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotNull
  @OneToOne
  @JoinColumn(name = "user_id", nullable = false)
  private User user;

  @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<CartItem> cartItems = new ArrayList<>();

  @Column(name = "total_price", nullable = false)
  private BigDecimal totalPrice = BigDecimal.ZERO;

  @Column(name = "total_items", nullable = false)
  private int totalItems = 0;

  @Column(name = "active", nullable = false)
  private boolean active = false;

  public Cart(Long id, User user, List<CartItem> cartItems, BigDecimal totalPrice, int totalItems,
      boolean active) {
    this.id = id;
    this.user = user;
    this.cartItems = cartItems;
    this.totalPrice = totalPrice;
    this.totalItems = totalItems;
    this.active = active;
  }

  public Cart() {}

  public Long getId() {    return id;  }
  public void setId(Long id) {    this.id = id;  }
  public User getUser() {    return user;  }
  public void setUser(User user) {    this.user = user;  }
  public List<CartItem> getCartItems() {    return cartItems;  }
  public void setCartItems(List<CartItem> cartItems) {    this.cartItems = cartItems;  }
  public BigDecimal getTotalPrice() {    return totalPrice;  }
  public void setTotalPrice(BigDecimal totalPrice) {    this.totalPrice = totalPrice;  }
  public int getTotalItems() {    return totalItems;  }
  public void setTotalItems(int totalItems) {    this.totalItems = totalItems;  }

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
    if (!(o instanceof Cart cart)) {
      return false;
    }
    return getTotalItems() == cart.getTotalItems() && isActive() == cart.isActive()
        && Objects.equals(getId(), cart.getId()) && Objects.equals(getUser(),
        cart.getUser()) && Objects.equals(getCartItems(), cart.getCartItems())
        && Objects.equals(getTotalPrice(), cart.getTotalPrice());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getId(), getUser(), getCartItems(), getTotalPrice(), getTotalItems(),
        isActive());
  }

  @Override
  public String toString() {
    return "Cart{" +
        "id=" + id +
        ", user=" + user +
        ", cartItems=" + cartItems +
        ", totalPrice=" + totalPrice +
        ", totalItems=" + totalItems +
        ", active=" + active +
        '}';
  }
}
