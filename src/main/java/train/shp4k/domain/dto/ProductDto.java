package train.shp4k.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * 07/12/2024 shp4k
 *
 * @author Boris Iurciuc (cohort36)
 */
@Schema(description = "Class that describes Product") // add for Swagger
public class ProductDto {

  @Schema( // add for Swagger
      description = "Product unique identifier",
      example = "111",
      accessMode = Schema.AccessMode.READ_ONLY
  )
  private Long id;

  @JsonProperty("title")
  @NotBlank(message = "Title is required")
  private String title;

  @JsonProperty("description")
  private String description;

  @JsonProperty("price")
  @NotNull
  @Positive(message = "Price must be greater than 0")
  private BigDecimal price;

  @JsonProperty("image")
  private String image;

  @JsonProperty("stock_quantity")
  private int stockQuantity;

  @JsonProperty("discount")
  private BigDecimal discount;

  public ProductDto() {}

  public ProductDto(Long id, String title, String description, BigDecimal price, String image, int stockQuantity) {
    this.id = id;
    this.title = title;
    this.description = description;
    this.price = price;
    this.image = image;
    this.stockQuantity = stockQuantity;
  }

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

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
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

  public int getStockQuantity() { return stockQuantity; }

  public void setStockQuantity(int stockQuantity) { this.stockQuantity = stockQuantity; }

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
    if (!(o instanceof ProductDto that)) {
      return false;
    }
    return getStockQuantity() == that.getStockQuantity() && Objects.equals(getId(),
        that.getId()) && Objects.equals(getTitle(), that.getTitle())
        && Objects.equals(getDescription(), that.getDescription())
        && Objects.equals(getPrice(), that.getPrice()) && Objects.equals(
        getImage(), that.getImage()) && Objects.equals(getDiscount(), that.getDiscount());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getId(), getTitle(), getDescription(), getPrice(), getImage(),
        getStockQuantity(), getDiscount());
  }

  @Override
  public String toString() {
    return String.format("Product: id - %d, title - %s, price - %s, description - %s, "
            + "stockQuantity - %s, discount - %s", id, title, price, description, stockQuantity, discount);
  }

}
