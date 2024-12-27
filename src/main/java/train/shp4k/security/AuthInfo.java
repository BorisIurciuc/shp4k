package train.shp4k.security;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import train.shp4k.domain.entity.Role;

/**
 * 27/12/2024 shp4k
 *
 * @author Boris Iurciuc (cohort36)
 */
public class AuthInfo implements Authentication {

  private boolean authenticated;
  private final String username;
  private final Set<Role> roles;

  public AuthInfo(String username, Set<Role> roles) {
    this.username = username;
    this.roles = roles;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return roles;
  }

  @Override
  public Object getCredentials() {
    return null;
  }

  @Override
  public Object getDetails() {
    return null;
  }

  @Override
  public Object getPrincipal() {
    return username;
  }

  @Override
  public boolean isAuthenticated() {
    return authenticated;
  }

  @Override
  public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {

  }

  @Override
  public String getName() {
    return username;
  }
}
