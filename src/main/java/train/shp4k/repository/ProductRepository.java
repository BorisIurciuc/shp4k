package train.shp4k.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import train.shp4k.domain.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}