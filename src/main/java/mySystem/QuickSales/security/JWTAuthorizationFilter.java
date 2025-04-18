package mySystem.QuickSales.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import javax.crypto.SecretKey;
import static mySystem.QuickSales.security.TokensUtils.GENERATED_TOKEN_SECRET;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.stereotype.Component;

//Filtro para el proceso de autorizacion cuando el cliente desee usar el token
@Component // se la anota como componente para que pueda usarse como un objeto gestiionado por el nucleo de spring
public class JWTAuthorizationFilter extends BasicAuthenticationFilter{

    public JWTAuthorizationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }
    
  @Override
  protected void doFilterInternal(HttpServletRequest request, //solicitud
          HttpServletResponse response,
          FilterChain filterChain) throws ServletException, IOException {
    //Dentro de este metodo podemos utilizar repositorios, como usuario repository
    //Poder cargar los datos como pueden ser los roles, los permisos u otro dato necesario
    //de verificar
    
    String bearerToken = request.getHeader("Authorization");
    
    if(bearerToken == null || !bearerToken.startsWith("Bearer ")){
        filterChain.doFilter(request, response);
        return;
    }
    
    String token = bearerToken.replace("Bearer ", "");
    
    try{
        Claims claims = Jwts.parser()
                .verifyWith(getSigninKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();

        String username = claims.getSubject();
//        String username2 = (String) claims.get("username");
        Object authoritiesClaims = claims.get("authorities");
        
        Collection<? extends GrantedAuthority> authorities = Arrays.asList(
                new ObjectMapper()
                    .addMixIn(SimpleGrantedAuthority.class, SimpleGrantedAuthorityJsonCreator.class)
                    .readValue(authoritiesClaims.toString().getBytes(),SimpleGrantedAuthority[].class));
        UsernamePasswordAuthenticationToken usernamePAT = new UsernamePasswordAuthenticationToken(username, null, authorities);
        SecurityContextHolder.getContext().setAuthentication(usernamePAT);
        filterChain.doFilter(request, response);
    }catch(JwtException e){
        System.err.println(e.getMessage());
        Map<String, String> body = new HashMap<>();
        body.put("message", "El token JWT no es valido");

        response.getWriter().write(new ObjectMapper().writeValueAsString(body));
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setContentType("application/json");
        //esta validacion se usa en caso de que nos envien un token en formato incorrecto o con la informacion incorrecta
    }
  }
  
  private SecretKey getSigninKey() {
      byte[] keyBytes = Decoders.BASE64URL.decode(GENERATED_TOKEN_SECRET);
      return Keys.hmacShaKeyFor(keyBytes);
  }
  
}