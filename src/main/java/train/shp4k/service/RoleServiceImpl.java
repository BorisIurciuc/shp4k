package train.shp4k.service;

import org.springframework.stereotype.Service;
import train.shp4k.domain.entity.Role;
import train.shp4k.repository.RoleRepository;
import train.shp4k.service.interfaces.RoleService;

/**
 * 26/12/2024 shp4k
 *
 * @author Boris Iurciuc (cohort36)
 */

@Service
public class RoleServiceImpl implements RoleService {

  private final RoleRepository repository;
  public RoleServiceImpl(RoleRepository repository) {
    this.repository = repository;
  }


  @Override
  public Role getRoleAdmin() {
    return repository.findByTitle("ROLE_ADMIN").orElseThrow(
        () -> new RuntimeException("Role Admin not found")
    );
  }

  @Override
  public Role getRoleUser() {
    return repository.findByTitle("ROLE_USER").orElseThrow(
        () -> new RuntimeException("Role User not found")
    );
  }
}
