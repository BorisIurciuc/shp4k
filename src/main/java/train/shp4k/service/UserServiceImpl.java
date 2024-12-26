package train.shp4k.service;

import java.util.List;
import java.util.Optional;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import train.shp4k.domain.dto.UserDto;
import train.shp4k.domain.entity.User;
import train.shp4k.repository.UserRepository;
import train.shp4k.service.interfaces.UserService;
import train.shp4k.service.mapping.UserMappingService;

/**
 * 14/12/2024 shp4k
 *
 * @author Boris Iurciuc
 */
@Service
public class UserServiceImpl implements UserService {

  private final UserRepository repository;
  private final UserMappingService mappingService;

  public UserServiceImpl(UserRepository repository, UserMappingService userMappingService) {
    this.repository = repository;
    this.mappingService = userMappingService;
  }

  @Override
  public List<UserDto> getUsers() {
    return repository.findAll()
        .stream()
        .map(mappingService::mapEntityToDto)
        .toList();
  }

  @Override
  public UserDto getUserById(Long id) {
    User user = repository.findById(id).orElse(null);
    return mappingService.mapEntityToDto(user);
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return repository.findByUsername(username)
        .orElseThrow(
            () -> new UsernameNotFoundException(String.format("User %s not found", username)));
  }
}
