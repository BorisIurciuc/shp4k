package train.shp4k.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import train.shp4k.domain.entity.CartItem;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {

  Optional<CartItem> findByCartIdAndProductId(Long cartId, Long productId);
}
