package train.shp4k.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import train.shp4k.domain.entity.User;

public interface UserRepository extends JpaRepository <User, Long> {

}
