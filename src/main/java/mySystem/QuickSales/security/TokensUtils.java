package mySystem.QuickSales.security;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import java.util.Collection;

import java.util.Date;
import javax.crypto.SecretKey;
import org.springframework.security.core.GrantedAuthority;

public class TokensUtils {
    public final static SecretKey ACCESS_TOKEN_SECRET = Jwts.SIG.HS256.key().build(); // Llave secreta de acceso
    private final static Long ACCESS_TOKEN_VALIDITY_SECONDS = 86400000L; // tiempo de validez del token a 5 minutos expresado en milisegundos

    //Este metodo creara el token que sera enviado al cliente
    public static String createAccessToken(String username, Collection<? extends GrantedAuthority> roles) throws JsonProcessingException{
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
}
