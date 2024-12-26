package train.shp4k.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import train.shp4k.domain.entity.Role;

/**
 * 26/12/2024 shp4k
 *
 * @author Boris Iurciuc (cohort36)
 */

public interface RoleRepository extends JpaRepository<Role, Long> {
  Optional<Role> findByTitle(String title); // Optional - удобно, чтобы не получить null pointer exception
}
