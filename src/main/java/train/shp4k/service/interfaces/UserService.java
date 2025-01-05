package train.shp4k.service.interfaces;

import java.util.List;
import java.util.Optional;
import org.springframework.security.core.userdetails.UserDetailsService;
import train.shp4k.domain.dto.UserDto;
import train.shp4k.domain.entity.User;

public interface UserService extends UserDetailsService {

  List<UserDto> getUsers();
  UserDto getUserById(Long id);
  void register(User user);
  void registrationConfirm(String code);


}
