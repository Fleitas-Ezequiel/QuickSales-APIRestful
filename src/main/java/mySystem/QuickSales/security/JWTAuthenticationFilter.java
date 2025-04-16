package mySystem.QuickSales.security;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import mySystem.QuickSales.model.User;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter{
    
    private final AuthenticationManager authenticationManager;
    
    public JWTAuthenticationFilter(AuthenticationManager authenticationManager){
        this.authenticationManager = authenticationManager;
    }
    
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
            HttpServletResponse response) throws AuthenticationException{

      User usuario = null;
      String username = null;
      String password = null;

      try{
        //recibimos los datos de autenticacion en un json y lo mapeamos a un formato legible
        usuario = new ObjectMapper().readValue(request.getInputStream(), User.class);
        username = usuario.getUsername();
        password = usuario.getPassword();
      }catch(StreamReadException e){
          e.getStackTrace();
      }
      catch(DatabindException e){
          e.printStackTrace();
      }
      catch(IOException e){
          e.printStackTrace();
      }

      UsernamePasswordAuthenticationToken usernamePAT = new UsernamePasswordAuthenticationToken(
              username,
              password
      );
      return authenticationManager.authenticate(usernamePAT);
    }

    @Override
    protected void successfulAuthentication(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain chain,
            Authentication authResult) throws IOException, ServletException{
        
      Collection<? extends GrantedAuthority> roles = authResult.getAuthorities();

      
      org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User) authResult.getPrincipal();
      String username = user.getUsername();
      String token  = TokensUtils.createAccessToken(username, roles);
      
      response.addHeader("Authorization", "Bearer " + token);
      
      Map<String, String> body = new HashMap<>();
      body.put("token", token);
      body.put("username", username);
      body.put("message", String.format("Usuario %s logueado con exito", username));
      
      response.getWriter().write(new ObjectMapper().writeValueAsString(body));
      response.setContentType("application/json");
      response.setStatus(200);
    }
    
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request,
            HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException{
        Map<String, String> body = new HashMap<>();
        body.put("message", "Error en la autenticacion. Credenciales incorrectas");
        body.put("error", failed.getMessage());
        
        response.getWriter().write(new ObjectMapper().writeValueAsString(body));
        response.setContentType("application/json");
        response.setStatus(401);
    }
  
}
