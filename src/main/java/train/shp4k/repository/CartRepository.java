package train.shp4k.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import train.shp4k.domain.entity.Cart;

/**
 * 22/12/2024 shp4k
 *
 * @author Boris Iurciuc (cohort36)
 */

public interface CartRepository extends JpaRepository<Cart, Long> {

}
