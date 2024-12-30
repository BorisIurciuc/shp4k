package train.shp4k.service.interfaces;

import train.shp4k.domain.entity.User;

public interface ConfirmationService {

  String generateConfirmationCode(User user);

  User getUserByConfirmationCode(String code);

}
