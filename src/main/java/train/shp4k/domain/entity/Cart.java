package train.shp4k.domain.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
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

  @OneToOne
  @JoinColumn(name = "user_id", nullable = false)
  private User user;

  @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<CartItem> cartItems = new ArrayList<>();

  public Cart(Long id, User user, List<CartItem> cartItems) {
    this.id = id;
    this.user = user;
    this.cartItems = cartItems;
  }

  public Cart() {}

  public Long getId() {    return id;  }
  public void setId(Long id) {    this.id = id;  }
  public User getUser() {    return user;  }
  public void setUser(User user) {    this.user = user;  }
  public List<CartItem> getCartItems() {    return cartItems;  }
  public void setCartItems(List<CartItem> cartItems) {    this.cartItems = cartItems;  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Cart cart)) {
      return false;
    }
    return Objects.equals(getId(), cart.getId()) && Objects.equals(getUser(),
        cart.getUser()) && Objects.equals(getCartItems(), cart.getCartItems());
  }
  @Override
  public int hashCode() {
    return Objects.hash(getId(), getUser(), getCartItems());
  }

  @Override
  public String toString() {
    return "Cart{" +
        "id=" + id + ", user=" + user + ", cartItems=" + cartItems + '}';
  }
}
