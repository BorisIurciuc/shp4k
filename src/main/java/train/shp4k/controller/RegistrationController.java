package train.shp4k.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import train.shp4k.domain.entity.User;
import train.shp4k.exception_handling.Response;
import train.shp4k.service.interfaces.UserService;

/**
 * 30/12/2024 shp4k
 *
 * @author Boris Iurciuc (cohort36)
 */

@RestController
@RequestMapping("api/register")
public class RegistrationController {

  private final UserService service;
  public RegistrationController(UserService service) {
    this.service = service;
  }

  @PostMapping
  public Response register(@RequestBody User user) {
    service.register(user);
    return new Response("Register successful. Please check your email.");
  }

  @GetMapping
  public Response registrationConfirm(@RequestParam String code) {
    service.registrationConfirm(code);
    return new Response("Registration confirmed successfully");
  }

}
