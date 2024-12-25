package train.shp4k.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import train.shp4k.domain.entity.User;

@Repository
public interface UserRepository extends JpaRepository <User, Long> {

}
