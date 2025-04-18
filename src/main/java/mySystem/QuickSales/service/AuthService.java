package mySystem.QuickSales.service;


import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import lombok.AllArgsConstructor;
import mySystem.QuickSales.model.AuthenticationResponse;
import mySystem.QuickSales.model.Role;
import mySystem.QuickSales.model.Token;
import mySystem.QuickSales.model.User;
import mySystem.QuickSales.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import mySystem.QuickSales.repository.TokenRepository;
import mySystem.QuickSales.security.TokensUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;

@Service
@AllArgsConstructor
public class AuthService {
  
  @Autowired
  private final TokenRepository tokenRepository;
  
  @Autowired
  private final UserRepository userRepository;
  
  @Autowired
  private final PasswordEncoder passwordEncoder;
  
  @Autowired
  private final TokensUtils jwtUtils;
  
  @Autowired
  private final AuthenticationManager authenticationManager;
  
  public AuthenticationResponse register(User request) throws JsonProcessingException {

        // check if user already exist. if exist than authenticate the user
        if(userRepository.findByUsername(request.getUsername()).isPresent()) {
            return new AuthenticationResponse(null, null,"User already exist");
        }

        User user = new User();
        user.setEmail(request.getEmail());
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));


        user.setRoles(request.getRoles());

        user = userRepository.save(user);
        
        Collection<? extends GrantedAuthority> roles = buildAuthorities(user.getRoles());

        String accessToken = jwtUtils.createAccessToken(user.getUsername(), roles);
        String refreshToken = jwtUtils.createRefreshToken(user.getUsername(), roles);

        saveUserToken(accessToken, refreshToken, user);

        return new AuthenticationResponse(accessToken, refreshToken,"User registration was successful");

    }

    public AuthenticationResponse authenticate(User request) throws JsonProcessingException {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        User user = userRepository.findByUsername(request.getUsername()).orElseThrow();
        
        Collection<? extends GrantedAuthority> roles = buildAuthorities(user.getRoles());
        
        String accessToken = jwtUtils.createAccessToken(user.getUsername(), roles);
        String refreshToken = jwtUtils.createRefreshToken(user.getUsername(), roles);

        revokeAllTokenByUser(user);
        saveUserToken(accessToken, refreshToken, user);

        return new AuthenticationResponse(accessToken, refreshToken, "User login was successful");

    }
    
    private Collection<? extends GrantedAuthority> buildAuthorities(List<Role> roleNames) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (Role role : roleNames) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        return authorities;
    }
    
    private void revokeAllTokenByUser(User user) {
        List<Token> validTokens = tokenRepository.findAllAccessTokensByUser(user.getIdUser());
        if(validTokens.isEmpty()) {
            return;
        }

        validTokens.forEach(t-> {
            t.setLoggedOut(true);
        });

        tokenRepository.saveAll(validTokens);
    }
    private void saveUserToken(String accessToken, String refreshToken, User user) {
        Token token = new Token();
        token.setAccessToken(accessToken);
        token.setRefreshToken(refreshToken);
        token.setLoggedOut(false);
        token.setUser(user);
        tokenRepository.save(token);
    }

    public ResponseEntity refreshToken(
            HttpServletRequest request,
            HttpServletResponse response) throws JsonProcessingException {
        // extract the token from authorization header
        String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

        if(authHeader == null || !authHeader.startsWith("Bearer ")) {
            System.out.println("No coincide");
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }

        String token = authHeader.substring(7);

        // extract username from token
        String username = jwtUtils.extractUsername(token);

        // check if the user exist in database
        User user = userRepository.findByUsername(username)
                .orElseThrow(()->new RuntimeException("No user found"));

        // check if the token is valid
        if(jwtUtils.isValidRefreshToken(token, user)) {
            // generate access token
            
            Collection<? extends GrantedAuthority> roles = buildAuthorities(user.getRoles());
            
            String accessToken = jwtUtils.createAccessToken(user.getUsername(), roles);
            String refreshToken = jwtUtils.createRefreshToken(user.getUsername(), roles);

            revokeAllTokenByUser(user);
            saveUserToken(accessToken, refreshToken, user);

            return new ResponseEntity(new AuthenticationResponse(accessToken, refreshToken, "New token generated"), HttpStatus.OK);
        }
        
        return new ResponseEntity(HttpStatus.UNAUTHORIZED);

    }
}
