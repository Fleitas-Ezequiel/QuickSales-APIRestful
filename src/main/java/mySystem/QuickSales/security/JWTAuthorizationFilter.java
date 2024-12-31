package mySystem.QuickSales.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

//Filtro para el proceso de autorizacion cuando el cliente desee usar el token
@Component // se la anota como componente para que pueda usarse como un objeto gestiionado por el nucleo de spring
public class JWTAuthorizationFilter extends OncePerRequestFilter{

  @Override
  protected void doFilterInternal(HttpServletRequest request, //solicitud
          HttpServletResponse response,
          FilterChain filterChain) throws ServletException, IOException {
    //Dentro de este metodo podemos utilizar repositorios, como usuario repository
    //Poder cargar los datos como pueden ser los roles, los permisos u otro dato necesario
    //de verificar
    
    String bearerToken = request.getHeader("Authorization");
    
    if(bearerToken != null && bearerToken.startsWith("Bearer ")){
      String token = bearerToken.replace("Bearer ", "");
      UsernamePasswordAuthenticationToken usernamePAT = TokensUtils.getAuthentication(token);
      SecurityContextHolder.getContext().setAuthentication(usernamePAT);
    }
    filterChain.doFilter(request, response);
  
  }
  
}