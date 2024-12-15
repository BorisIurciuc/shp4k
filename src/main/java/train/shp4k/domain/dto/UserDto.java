package train.shp4k.domain.dto;

import java.util.Objects;

/**
 * 14/12/2024 shp4k
 *
 * @author Boris Iurciuc
 */
public class UserDto {

  private Long id;

  private String username;

  private String email;

  private String password;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof UserDto userDto)) {
      return false;
    }
    return Objects.equals(getId(), userDto.getId()) && Objects.equals(
        getUsername(), userDto.getUsername()) && Objects.equals(getEmail(),
        userDto.getEmail()) && Objects.equals(getPassword(), userDto.getPassword());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getId(), getUsername(), getEmail(), getPassword());
  }

  @Override
  public String toString() {
    return "UserDto{" +
        "id=" + id +
        ", username='" + username + '\'' +
        ", email='" + email + '\'' +
        ", password='" + password + '\'' +
        '}';
  }
}
