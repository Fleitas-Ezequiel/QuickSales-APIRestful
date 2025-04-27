package mySystem.QuickSales.security;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.util.Collection;

import java.util.Date;
import java.util.function.Function;
import javax.crypto.SecretKey;
import mySystem.QuickSales.model.User;
import mySystem.QuickSales.repository.TokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class TokensUtils {
    public final static String GENERATED_TOKEN_SECRET = ""; // Llave secreta de acceso
    private final static long ACCESS_TOKEN_VALIDITY_SECONDS = 15*60*1000; // tiempo de validez del token a 5 minutos expresado en milisegundos
    private final static long REFRESH_TOKEN_VALIDITY_SECONDS = 14*24*60*60*1000;
    
    @Autowired
    private final TokenRepository tokenRepository;

    public TokensUtils(TokenRepository tokenRepository) {
      this.tokenRepository = tokenRepository;
    }
     
    //Este metodo creara el token que sera enviado al cliente
    public static String createAccessToken(String username, Collection<? extends GrantedAuthority> roles) throws JsonProcessingException{
      return generateToken(username, roles, ACCESS_TOKEN_VALIDITY_SECONDS);
    }
    
    public static String createRefreshToken(String username, Collection<? extends GrantedAuthority> roles) throws JsonProcessingException{
      return generateToken(username, roles, REFRESH_TOKEN_VALIDITY_SECONDS);
    }
    
    private static String generateToken(String username, 
            Collection<? extends GrantedAuthority> roles,
            Long expiration)throws JsonProcessingException{
      
      Claims claims = Jwts.claims()
              .add("authorities", new ObjectMapper().writeValueAsString(roles))
              .add("usuario", username)
              .build();
      
      return Jwts.builder()
              .subject(username)
              .claims(claims)
              .issuedAt(new Date(System.currentTimeMillis()))
              .expiration(new Date(System.currentTimeMillis() + expiration))
              .signWith(getSigninKey())
              .compact();
    }
    
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }
    
    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }
    
    public <T> T extractClaim(String token, Function<Claims, T> resolver) {
        Claims claims = extractAllClaims(token);
        return resolver.apply(claims);
    }
    
    private Claims extractAllClaims(String token) {
        return Jwts
                .parser()
                .verifyWith(getSigninKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
    
    private static SecretKey getSigninKey() {
        byte[] keyBytes = Decoders.BASE64URL.decode(GENERATED_TOKEN_SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }
    
    public boolean isValidRefreshToken(String token, User user) {
        String username = extractUsername(token);
        
        boolean validRefreshToken = tokenRepository
                .findByRefreshToken(token)
                .map(t -> !t.isLoggedOut())
                .orElse(false);

        return (username.equals(user.getUsername())) && !isTokenExpired(token) && validRefreshToken;
    }
    
    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }
    
    public boolean isValid(String token, UserDetails user) {
        String username = extractUsername(token);
        
        boolean validToken = tokenRepository
                .findByAccessToken(token)
                .map(t -> !t.isLoggedOut())
                .orElse(false);
        
        return (username.equals(user.getUsername())) && !isTokenExpired(token) && validToken;
    }
}
