package mySystem.QuickSales.service;


import java.time.Instant;
import java.util.ArrayList;
import java.util.UUID;
import mySystem.QuickSales.model.RefreshToken;
import mySystem.QuickSales.model.User;
import mySystem.QuickSales.repository.RefreshTokenRepository;
import mySystem.QuickSales.repository.UserRepository;
import mySystem.QuickSales.security.JwtUtils;
import mySystem.QuickSales.security.TokensUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
  
  @Autowired
  private RefreshTokenRepository refreshTokenRepository;
  
  @Autowired
  private UserRepository userRepository;
  
  @Autowired
  private JwtUtils jwtUtils;
  
  private long refreshTokenDurationMs = 14 * 24 * 60 * 60 * 1000;
  
  public RefreshToken createRefreshToken(User user){
    RefreshToken token = new RefreshToken();
    token.setUser(user);
    token.setToken(UUID.randomUUID().toString());
    token.setExpiryDate(Instant.now().plusMillis(refreshTokenDurationMs));
    return refreshTokenRepository.save(token);
  }
  
  public RefreshToken verifyExpiration(RefreshToken token){
    if(token.getExpiryDate().isBefore(Instant.now())){
      refreshTokenRepository.delete(token);
      throw new RuntimeException("Refresh token expirado");
    }
    return token;
  }
  
  public String refreshAccessToken(String refreshTokenStr){
    RefreshToken token = refreshTokenRepository.findByToken(refreshTokenStr)
            .map(this::verifyExpiration)
            .orElseThrow(()-> new RuntimeException("Token no encontrado"));
    
    User user = token.getUser();
    return jwtUtils.generateJwtToken(new org.springframework.security.core.userdetails.User(
                user.getUsername(), user.getPassword(), new ArrayList<>()));
  }
  
  public TokenResponse refreshToken(final String authHeader){
    if(authHeader == null || !authHeader.startsWith("Bearer ")){
      throw new IllegalArgumentException("Invalid Bearer token");
    }
  }
}
