package train.shp4k.repository;


import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import train.shp4k.domain.entity.ConfirmationCode;

public interface ConfirmationCodeRepository extends JpaRepository<ConfirmationCode, Long> {

  Optional<ConfirmationCode> findByCode(String code);

}