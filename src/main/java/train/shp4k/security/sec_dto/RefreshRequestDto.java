package train.shp4k.security.sec_dto;

import java.util.Objects;

/**
 * 27/12/2024 shp4k
 *
 * @author Boris Iurciuc (cohort36)
 */
public class RefreshRequestDto {

  private String refreshToken;
  public String getRefreshToken() {
    return refreshToken;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof RefreshRequestDto that)) {
      return false;
    }
    return Objects.equals(refreshToken, that.refreshToken);
  }

  @Override
  public int hashCode() {    return Objects.hashCode(refreshToken);  }

  @Override
  public String toString() {    return "RefreshRequestDto{" + "refreshToken='" + refreshToken + '\'' + '}';
  }
}
