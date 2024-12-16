package train.shp4k.controller;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import train.shp4k.domain.dto.UserDto;
import train.shp4k.domain.entity.User;
import train.shp4k.service.interfaces.UserService;

/**
 * 14/12/2024 shp4k
 *
 * @author Boris Iurciuc
 */

@RestController
@RequestMapping("api/users")
public class UserController {

  private final UserService service;

  public UserController(UserService service) {
    this.service = service;
  }

  @GetMapping
  public List<UserDto> getAllUsers() {
    return service.getUsers();
  }

  @GetMapping("{id}")
  public ResponseEntity<UserDto> getUserById(@PathVariable Long id) {
    UserDto user = service.getUserById(id);
    return ResponseEntity.ok(user);
  }

}
