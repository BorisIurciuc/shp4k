package train.shp4k.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import train.shp4k.domain.entity.CartItem;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {

}
