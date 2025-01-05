package train.shp4k.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import train.shp4k.domain.dto.UserDto;
import train.shp4k.domain.entity.User;
import train.shp4k.repository.UserRepository;
import train.shp4k.service.interfaces.ConfirmationService;
import train.shp4k.service.interfaces.EmailService;
import train.shp4k.service.interfaces.RoleService;
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
  private final RoleService roleService;
  private final BCryptPasswordEncoder encoder;
  private final EmailService emailService;
  private final ConfirmationService confirmationService;

  public UserServiceImpl(UserRepository repository, UserMappingService mappingService,
      RoleService roleService, BCryptPasswordEncoder encoder, EmailService emailService,
      ConfirmationService confirmationService) {
    this.repository = repository;
    this.mappingService = mappingService;
    this.roleService = roleService;
    this.encoder = encoder;
    this.emailService = emailService;
    this.confirmationService = confirmationService;
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

  @Override
  public void register(User user) {
    String username = user.getUsername();
    User existedUser = repository.findByUsername(username).orElse(null);

    if (existedUser != null && existedUser.isActive()) {
      throw new RuntimeException("User already exists");
    }

    if (existedUser == null) {
      existedUser = new User();
      existedUser.setUsername(username);
      existedUser.setRoles(Set.of(roleService.getRoleUser()));
    }

    existedUser.setPassword(encoder.encode(user.getPassword()));
    existedUser.setEmail(user.getEmail());

    repository.save(existedUser);
    emailService.sendConfirmationEmail(existedUser);
  }

  @Override
  @Transactional
  public void registrationConfirm(String code) {
    User user = confirmationService.getUserByConfirmationCode(code);
    user.setActive(true);
  }
}
