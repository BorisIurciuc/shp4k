package train.shp4k.domain.dto;

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

  @NotBlank(message = "Title is required")
  private String title;

  private String description;

  @NotNull
  @Positive(message = "Price must be greater than 0")
  private BigDecimal price;

  private String image;

  public ProductDto() {}

  public ProductDto(Long id, String title, String description, BigDecimal price, String image) {
    this.id = id;
    this.title = title;
    this.description = description;
    this.price = price;
    this.image = image;
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

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof ProductDto that)) {
      return false;
    }
    return Objects.equals(getId(), that.getId()) && Objects.equals(getTitle(),
        that.getTitle()) && Objects.equals(getDescription(), that.getDescription())
        && Objects.equals(getPrice(), that.getPrice()) && Objects.equals(
        getImage(), that.getImage());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getId(), getTitle(), getDescription(), getPrice(), getImage());
  }

  @Override
  public String toString() {
    return String.format("Product: id - %d, title - %s, price - %s, description - %s",
        id, title, price, description);
  }

}
