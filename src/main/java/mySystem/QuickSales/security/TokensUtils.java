package mySystem.QuickSales.security;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;

import java.util.Date;
import javax.crypto.SecretKey;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

public class TokensUtils {
    private final static SecretKey ACCESS_TOKEN_SECRET = Jwts.SIG.HS256.key().build(); // Llave secreta de acceso
    private final static Long ACCESS_TOKEN_VALIDITY_SECONDS = 300_000L; // tiempo de validez del token a 5 minutos expresado en milisegundos

    //Este metodo creara el token que sera enviado al cliente
    public static String createToken(String username, Collection<? extends GrantedAuthority> roles) throws JsonProcessingException{
      Date expirationDate = new Date(System.currentTimeMillis() + ACCESS_TOKEN_VALIDITY_SECONDS);
      
      Claims claims = Jwts.claims()
              .add("authorities", new ObjectMapper().writeValueAsString(roles))
              .add("usuario", username)
              .build();
      
      return Jwts.builder()
              .subject(username)
              .claims(claims)
              .expiration(expirationDate)
              .issuedAt(new Date())
              .signWith(ACCESS_TOKEN_SECRET)
              .compact();
    }

    public static UsernamePasswordAuthenticationToken getAuthentication(String token) throws IOException{
      try{  
        Claims claims = Jwts.parser()
                .verifyWith(ACCESS_TOKEN_SECRET)
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

        return new UsernamePasswordAuthenticationToken(username, null, (Collection<? extends GrantedAuthority>) authorities);
      }catch(JwtException e){
        System.err.println(e.getMessage());
        return null; // especificamos que devuelva null para indicar que ningun UsernamePasswordAuthenticationToke a podido autenticarse a partir del token recibido
        //esta validacion se usa en caso de que nos envien un token en formato incorrecto o con la informacion incorrecta
      }
    }
}
