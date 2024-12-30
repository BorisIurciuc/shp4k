package train.shp4k.security.security_config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import train.shp4k.security.security_filter.TokenFilter;

/**
 * 15/12/2024 shp4k
 *
 * @author Boris Iurciuc (cohort36)
 */
@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

  private final TokenFilter filter;

  public SecurityConfig(TokenFilter filter) {    this.filter = filter;  }

  @Bean
  public BCryptPasswordEncoder encoder() {    return new BCryptPasswordEncoder();  }

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    return http
        .csrf(AbstractHttpConfigurer::disable)
        //.sessionManagement(x -> x.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED))
        .sessionManagement(x -> x.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .httpBasic(AbstractHttpConfigurer::disable)
        .addFilterAfter(filter, UsernamePasswordAuthenticationFilter.class) // добавили свой фильтр
        .authorizeHttpRequests(x -> x
            //products
            .requestMatchers(HttpMethod.GET, "/api/products").permitAll()
            .requestMatchers(HttpMethod.GET, "/api/products/{id}").hasAnyRole("USER", "ADMIN")
            .requestMatchers(HttpMethod.POST, "/api/products").hasRole("ADMIN")
            .requestMatchers(HttpMethod.PUT, "/api/products/{id}").hasRole("ADMIN")
            .requestMatchers(HttpMethod.DELETE, "/api/products/{id}").hasRole("ADMIN")
            //cart
            .requestMatchers(HttpMethod.GET, "/api/cart").permitAll()
            .requestMatchers(HttpMethod.GET, "/api/cart/{id}").hasAnyRole("USER", "ADMIN")
            .requestMatchers(HttpMethod.POST, "/api/cart").hasAnyRole("USER", "ADMIN")
            .requestMatchers(HttpMethod.DELETE, "/api/cart/{id}").hasRole("ADMIN")
            .requestMatchers(HttpMethod.DELETE, "/{cartId}/items/{cartItemId}").hasAnyRole("USER", "ADMIN")
            //user
            .requestMatchers(HttpMethod.GET, "/api/users").hasRole("ADMIN")
            .requestMatchers(HttpMethod.GET, "/api/users/{id}").hasAnyRole("USER", "ADMIN")
            //auth
            .requestMatchers(HttpMethod.POST, "/auth/login").permitAll()
            .requestMatchers(HttpMethod.POST, "/register").permitAll()
            .requestMatchers(HttpMethod.GET, "/register").permitAll()

        ).build();
  }
}
