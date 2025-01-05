package train.shp4k.security.security_controller;

/**
 * 29/12/2024 shp4k
 *
 * @author Boris Iurciuc (cohort36)
 */
import jakarta.security.auth.message.AuthException;
import org.springframework.web.bind.annotation.*;
import train.shp4k.domain.entity.User;
import train.shp4k.security.sec_dto.RefreshRequestDto;
import train.shp4k.security.sec_dto.TokenResponseDto;
import train.shp4k.security.security_service.AuthService;


@RestController
@RequestMapping("/auth")
public class AuthController {

  private final AuthService service;

  public AuthController(AuthService service) {
    this.service = service;
  }

  //endpoint для аутентификации пользователя
  @PostMapping("/login")
  public TokenResponseDto login(@RequestBody User user) {
    try {
      return service.login(user);
    } catch (AuthException e) {
      throw new RuntimeException(e);
    }
  }

  @PostMapping("/refresh")
  public TokenResponseDto getNewAccessToken(@RequestBody RefreshRequestDto request){
    try {
      return service.getNewAccessToken(request.getRefreshToken());
    } catch (AuthException e) {
      throw new RuntimeException(e);
    }
  }

}