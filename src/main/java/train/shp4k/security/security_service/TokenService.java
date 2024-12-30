package train.shp4k.security.security_service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;
import javax.crypto.SecretKey;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import train.shp4k.domain.entity.Role;
import train.shp4k.repository.RoleRepository;
import train.shp4k.security.AuthInfo;

/**
 * 27/12/2024 shp4k
 *
 * @author Boris Iurciuc (cohort36)
 */

@Service
public class TokenService {

  //fields
  private final SecretKey accessKey;
  private final SecretKey refreshKey;
  private final RoleRepository roleRepository;


  /**
   * Конструктор класса TokenService, инициализирующий ключи и репозиторий ролей.
   * @Value - аннотация для внедрения значений из файла конфигурации (application.properties или application.yml)
   * Генерация ключей с использованием HMAC SHA256 и декодирование строк из Base64
   * <a href="https://www.devglan.com/online-tools/hmac-sha256-online">...</a>
   */

  public TokenService(
      @Value("${key.access}") String accessSecretPhrase,
      @Value("${key.refresh}") String refreshSecretPhrase,
      RoleRepository roleRepository
  ) {
    this.accessKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(accessSecretPhrase));
    this.refreshKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(refreshSecretPhrase));
    this.roleRepository = roleRepository;
  }

  //// Generate access token
  public String generateAccessToken(UserDetails user) {
    LocalDateTime currentDate = LocalDateTime.now();
    Instant expirationInstant = currentDate.plusDays(7).atZone(ZoneId.systemDefault()).toInstant(); // Expires in 7 days
    Date expiration = Date.from(expirationInstant);

    return Jwts.builder()
        .subject(user.getUsername())
        .expiration(expiration)
        .signWith(accessKey)
        .claim("roles", user.getAuthorities())
        .claim("name", user.getUsername())
        .compact();
  }

  // Generate refresh token
  public String generateRefreshToken(UserDetails user) {
    LocalDateTime currentDate = LocalDateTime.now();
    Instant expirationInstant = currentDate.plusDays(30).atZone(ZoneId.systemDefault()).toInstant(); // Expires in 30 days
    Date expiration = Date.from(expirationInstant);

    return Jwts.builder()
        .subject(user.getUsername())
        .expiration(expiration)
        .signWith(refreshKey)
        .compact();
  }


  // Validate access token
  public boolean validateAccessToken(String accessToken) {
    return validateToken(accessToken, accessKey);
  }

  // Validate refresh token
  public boolean validateRefreshToken(String refreshToken) {
    return validateToken(refreshToken, refreshKey);
  }

  // Приватный метод для общей валидации токена с использованием ключа
  private boolean validateToken(String token, SecretKey key) {
    try {
      // Парсим токен и проверяем его подпись с помощью ключа
      Jwts.parser()
          .verifyWith(key)  // Проверяем подпись с использованием ключа
          .build()
          .parseSignedClaims(token);  // Парсим claims
      return true;  // Токен валиден
    } catch (Exception e) {
      return false;  // В случае ошибки токен не валиден
    }
  }

  // Метод для получения claims (данных) из access-токена
  public Claims getAccessClaims(String accessToken) {
    return getClaims(accessToken, accessKey);  // Вызываем общий метод для получения claims
  }

  // Метод для получения claims из refresh-токена
  public Claims getRefreshClaims(String refreshToken) {
    return getClaims(refreshToken, refreshKey);  // Вызываем общий метод для получения claims
  }

  // Приватный метод для получения claims из токена
  private Claims getClaims(String token, SecretKey key) {
    return Jwts.parser()
        .verifyWith(key)  // Проверяем подпись с использованием ключа
        .build()
        .parseSignedClaims(token)  // Парсим claims
        .getPayload();  // Возвращаем полезную нагрузку (claims)
  }

  // Метод для преобразования данных из claims в объект AuthInfo (который содержит имя пользователя и роли)
  public AuthInfo mapClaimsToAuthInfo(Claims claims) {
    String username = claims.getSubject();  // Получаем имя пользователя из claims

    // Получаем список ролей из claims, где каждая роль представлена как карта (authority: роль)
    List<LinkedHashMap<String, String>> roleList = (List<LinkedHashMap<String, String>>) claims.get("roles");
    Set<Role> roles = new HashSet<>();  // Множество для хранения ролей

    // Перебираем роли, извлекаем их название, ищем в базе данных и добавляем в множество
    for (LinkedHashMap<String, String> roleEntry : roleList) {
      String roleTitle = roleEntry.get("authority");  // Извлекаем название роли
      // Ищем роль в репозитории по названию. Если роль не найдена, выбрасываем исключение
      Role role = roleRepository.findByTitle(roleTitle).orElseThrow(
          () -> new RuntimeException("Database doesn't contain role")
      );
      roles.add(role);  // Добавляем роль в множество
    }

    // Возвращаем объект AuthInfo, который содержит имя пользователя и его роли
    return new AuthInfo(username, roles);
  }
}
