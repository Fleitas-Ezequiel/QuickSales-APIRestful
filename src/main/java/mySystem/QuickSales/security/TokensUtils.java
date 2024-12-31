package mySystem.QuickSales.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Collections;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class TokensUtils {
    private final static String ACCESS_TOKEN_SECRET = "4qhq8LrEBfYcaRHxhdb9zURb2rf8e7Ud"; // Llave secreta de acceso
    private final static Long ACCESS_TOKEN_VALIDITY_SECONDS = 2_592_000L; // tiempo de validez del token a 30 dias expresado en segundos

    //Este metodo creara el token que sera enviado al cliente
    public static String createToken(String nombre, String email){
      long expirationTime = ACCESS_TOKEN_VALIDITY_SECONDS * 1_000;
      Date expirationDate = new Date(System.currentTimeMillis() + expirationTime);

      Map<String, Object> extra = new HashMap<>();
      extra.put("nombre", nombre);
      return Jwts.builder()
              .setSubject(email)
              .setExpiration(expirationDate)
              .addClaims(extra)
              .signWith(SignatureAlgorithm.HS256,ACCESS_TOKEN_SECRET.getBytes())
              .compact();
    }

    public static UsernamePasswordAuthenticationToken getAuthentication(String token){
      try{  
        Claims claims = Jwts.parser()
                .setSigningKey(ACCESS_TOKEN_SECRET.getBytes())
                .parseClaimsJws(token)
                .getBody();

        String email = claims.getSubject();

        return new UsernamePasswordAuthenticationToken(email, null, Collections.emptyList());
      }catch(JwtException e){
        return null; // especificamos que devuelva null para indicar que ningun UsernamePasswordAuthenticationToke a podido autenticarse a partir del token recibido
        //esta validacion se usa en caso de que nos envien un token en formato incorrecto o con la informacion incorrecta
      }
    }
}
