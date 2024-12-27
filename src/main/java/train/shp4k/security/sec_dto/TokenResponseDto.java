package train.shp4k.security.sec_dto;

import java.util.Objects;

/**
 * 27/12/2024 shp4k
 *
 * @author Boris Iurciuc (cohort36)
 */
public class TokenResponseDto {

  private String accessToken;
  private String refreshToken;

  public TokenResponseDto(String accessToken, String refreshToken) {
    this.accessToken = accessToken;
    this.refreshToken = refreshToken;
  }

  public String getAccessToken() {
    return accessToken;
  }

  public void setAccessToken(String accessToken) {
    this.accessToken = accessToken;
  }

  public String getRefreshToken() {
    return refreshToken;
  }

  public void setRefreshToken(String refreshToken) {
    this.refreshToken = refreshToken;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof TokenResponseDto that)) {
      return false;
    }
    return Objects.equals(accessToken, that.accessToken) && Objects.equals(
        refreshToken, that.refreshToken);
  }

  @Override
  public int hashCode() {
    return Objects.hash(accessToken, refreshToken);
  }

  @Override
  public String toString() {
    return "TokenResponseDto{" +
        "accessToken='" + accessToken + '\'' +  ", refreshToken='" + refreshToken + '\'' + '}';
  }
}
