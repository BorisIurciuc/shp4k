package train.shp4k.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import train.shp4k.domain.entity.Cart;
import train.shp4k.domain.entity.User;

/**
 * 22/12/2024 shp4k
 *
 * @author Boris Iurciuc (cohort36)
 */

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

  Optional<Cart> findByUserAndActive(User user, boolean active);
}



