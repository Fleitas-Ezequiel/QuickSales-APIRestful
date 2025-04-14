package mySystem.QuickSales.controller;

// controller/AuthController.java

import lombok.RequiredArgsConstructor;
import mySystem.QuickSales.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import payload.JwtResponse;
import payload.TokenRefreshRequest;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    @Autowired
    private AuthService authService;
    
    @PostMapping("/login")
    public RespopnseEntity<TokenResponse> authenticate(@RequestBody final LoginRequest request){
      final TokenResponse token = authService.login(request);
      return ResponseEntity.ok(token);
    }

    @PostMapping("/refresh")
    public TokenResponse refreshToken(@RequestHeader(HttpHeaders.AUTHORIZATION) final String authHeader) {
        try {
            return authService.refreshToken(authHeader);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Token inválido o expirado");
        }
    }
}

