package train.shp4k.service;

import java.time.LocalDateTime;
import java.util.UUID;
import org.springframework.stereotype.Service;
import train.shp4k.domain.entity.ConfirmationCode;
import train.shp4k.domain.entity.User;
import train.shp4k.repository.ConfirmationCodeRepository;
import train.shp4k.service.interfaces.ConfirmationService;

/**
 * 29/12/2024 shp4k
 *
 * @author Boris Iurciuc (cohort36)
 */
@Service
public class ConfirmationServiceImpl implements ConfirmationService {

  private final ConfirmationCodeRepository repository;

  public ConfirmationServiceImpl(ConfirmationCodeRepository repository) {
    this.repository = repository;
  }

  @Override
  public String generateConfirmationCode(User user) {
    //LocalDateTime expired = LocalDateTime.now().plusDays(1); //production
    LocalDateTime expired = LocalDateTime.now().plusMinutes(60);

    //generate code
    String code = UUID.randomUUID().toString();

    //create object
    ConfirmationCode entity = new ConfirmationCode(code, expired, user);

    //save to repository
    repository.save(entity);

    return code;
  }

  @Override
  public User getUserByConfirmationCode(String code) {
    return null;
  }
}
