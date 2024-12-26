package train.shp4k.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import java.util.Collection;
import java.util.Objects;
import java.util.Set;
import jakarta.validation.constraints.NotBlank;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * 14/12/2024 shp4k
 *
 * @author Boris Iurciuc
 */
@Entity
@Table
public class User implements UserDetails {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", unique = true, nullable = false)
  private Long id;

  @Column(name = "username", unique = true, nullable = false)
  @NotBlank(message = "Username cannot be blank")
  private String username;

  @Column(name = "password", nullable = false)
  private String password;

  @Column(name = "email", unique = true, nullable = false)
  @NotBlank(message = "Email cannot be blank")
  @Email(message = "Email should be valid")
  private String email;

  @Column(name = "active",  nullable = false)
  private boolean active;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(name = "user_role",
    joinColumns = @JoinColumn(name = "user_id"),
    inverseJoinColumns = @JoinColumn(name="role_id")
  )
  private Set<Role> roles;


  public Long getId() {
    return id;
  }
  public void setId(Long id) {
    this.id = id;
  }
  @Override
  public String getUsername() {
    return username;
  }
  public void setUsername(String username) {
    this.username = username;
  }
  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return roles;
  }
  @Override
  public String getPassword() {
    return password;
  }
  public void setPassword(String password) {
    this.password = password;
  }
  public String getEmail() {
    return email;
  }
  public void setEmail(String email) {
    this.email = email;
  }
  public boolean isActive() {
    return active;
  }
  public void setActive(boolean active) {
    this.active = active;
  }
  public Set<Role> getRoles() {
    return roles;
  }
  public void setRoles(Set<Role> roles) {
    this.roles = roles;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof User user)) {
      return false;
    }
    return isActive() == user.isActive() && Objects.equals(getId(), user.getId())
        && Objects.equals(getUsername(), user.getUsername()) && Objects.equals(
        getPassword(), user.getPassword()) && Objects.equals(getEmail(), user.getEmail())
        && Objects.equals(getRoles(), user.getRoles());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getId(), getUsername(), getPassword(), getEmail(), isActive(), getRoles());
  }

  @Override
  public String toString() {
    return String.format("User: id - %d, username - %s, email - %s, roles - %s", id, username, email,
        roles == null ? "empty" : roles);
  }

//  //     Метод для получения зашифрованного пароля
////     для добавления пользователей в БД вручную
//  public static void main(String[] args) {
//    System.out.println(new BCryptPasswordEncoder().encode("111"));
//  }
}
