package train.shp4k.security.security_filter;

import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import java.io.IOException;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;
import train.shp4k.security.AuthInfo;
import train.shp4k.security.security_service.TokenService;

/**
 * 29/12/2024 shp4k
 *
 * @author Boris Iurciuc (cohort36)
 */
@Component
public class TokenFilter extends GenericFilterBean {

  //private final StringHttpMessageConverter stringHttpMessageConverter;
  private final TokenService service;

  public TokenFilter(StringHttpMessageConverter stringHttpMessageConverter, TokenService service) {
    //this.stringHttpMessageConverter = stringHttpMessageConverter;
    this.service = service;
  }

  // фильтр
  @Override
  public void doFilter(
      ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
    String token = getTokenFromRequest((HttpServletRequest) servletRequest); // можем сделать безопасный кастинг так как servletRequest расширяет HttpServletRequest

    if(token != null && service.validateAccessToken(token)){
      Claims claims = service.getAccessClaims(token);
      AuthInfo authInfo = service.mapClaimsToAuthInfo(claims);
      authInfo.setAuthenticated(true);
      SecurityContextHolder.getContext().setAuthentication(authInfo);
    }
    filterChain.doFilter(servletRequest, servletResponse); // отправляем запрос дальше по цепочке фильтров
  }

  private String getTokenFromRequest(HttpServletRequest request){
    // заголовок Authorization -> Bearer 2k3424bmn234bm2b34mb2m3b2m2b34
    String token = request.getHeader("Authorization");

    if(token != null && token.startsWith("Bearer ")){
      return token.substring(7); // отбросили Bearer
    }
    return null;
  }
}
