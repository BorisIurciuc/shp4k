package train.shp4k.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.util.Objects;


/**
 * 07/12/2024 shp4k
 *
 * @author Boris Iurciuc (cohort36)
 */

@Entity
@Table(name = "product")
public class Product {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @Column(name = "title")
  @NotBlank(message = "Title cannot be blank")
  private String title;

  @Column(name ="price")

  private BigDecimal price;

  @Column(name = "image")
  private String image;

  @Column(name = "description")
  private String description;

  @Column(name = "active")
  private boolean active;

  @Column(name = "stock_quantity")
  private int stockQuantity;

  @Column(name = "discount")
  private BigDecimal discount;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public BigDecimal getPrice() {
    return price;
  }

  public void setPrice(BigDecimal price) {
    this.price = price;
  }

  public String getImage() {
    return image;
  }

  public void setImage(String image) {
    this.image = image;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public boolean isActive() {
    return active;
  }

  public void setActive(boolean active) {
    this.active = active;
  }

  public int getStockQuantity() {
    return stockQuantity;
  }

  public void setStockQuantity(int stockQuantity) {
    this.stockQuantity = stockQuantity;
  }

  public BigDecimal getDiscount() {
    return discount;
  }

  public void setDiscount(BigDecimal discount) {
    this.discount = discount;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Product product)) {
      return false;
    }
    return isActive() == product.isActive() && getStockQuantity() == product.getStockQuantity()
        && Objects.equals(getId(), product.getId()) && Objects.equals(getTitle(),
        product.getTitle()) && Objects.equals(getPrice(), product.getPrice())
        && Objects.equals(getImage(), product.getImage()) && Objects.equals(
        getDescription(), product.getDescription()) && Objects.equals(getDiscount(),
        product.getDiscount());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getId(), getTitle(), getPrice(), getImage(), getDescription(), isActive(),
        getStockQuantity(), getDiscount());
  }

  @Override
  public String toString() {
    return String.format("Product: id - %d, title - %s, price - %s, description - %s, active - %s, stockQuantity - %s, discount - %s",
        id, title, price, description, active ? "yes" : "no", stockQuantity, discount);
  }
}
