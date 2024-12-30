package train.shp4k.service.interfaces;

import train.shp4k.domain.entity.User;

public interface EmailService {

  void sendConfirmationEmail(User user);

}
