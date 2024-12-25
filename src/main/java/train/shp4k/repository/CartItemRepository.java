package train.shp4k.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import train.shp4k.domain.entity.Cart;
import train.shp4k.domain.entity.CartItem;
import train.shp4k.domain.entity.Product;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
  Optional<CartItem> findByCartAndProduct(Cart cart, Product product);

  Optional<CartItem> findByCartIdAndProductId(Long cartId, Long productId);
}
