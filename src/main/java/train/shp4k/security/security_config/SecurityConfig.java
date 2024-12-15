package train.shp4k.security.security_config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
/**
 * 15/12/2024 shp4k
 *
 * @author Boris Iurciuc (cohort36)
 */
@Configuration
public class SecurityConfig {

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
        .csrf().disable()
        .authorizeHttpRequests(auth -> auth
            .anyRequest().permitAll() // Разрешить доступ ко всем эндпоинтам
        )
        .formLogin().disable(); // Отключить стандартную форму входа

    return http.build();
  }
}
