package mySystem.QuickSales.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    
    @Autowired
    private AuthenticationConfiguration authenticationConfiguration;
    
    @Bean
    AuthenticationManager authManager() throws Exception{
      return authenticationConfiguration.getAuthenticationManager();
    }
  
    @Bean
    //filtros de seguridad, se encarga de manejar las solicitudes de autenticación y autorización en la aplicación
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception{

      return http.authorizeHttpRequests( (authz) -> authz
              .requestMatchers("/auth/**").permitAll()
              .requestMatchers(HttpMethod.GET,"/user/list").permitAll()
              .requestMatchers(HttpMethod.POST,"/admin/**").hasRole("ADMIN")
              .requestMatchers("/proveedores/**").hasAnyRole("ADMIN","COMPRADOR")
              .requestMatchers("/comprobantes/**").hasAnyRole("ADMIN","COMPRADOR","CONTADOR")
              .requestMatchers("/pagos/**").hasAnyRole("ADMIN","COMPRADOR")
              .requestMatchers("/ventas/**").hasAnyRole("ADMIN","VENDEDOR")
              .requestMatchers("/caja/**").hasAnyRole("ADMIN","CONTADOR")
              .requestMatchers("/deposito/**").hasAnyRole("ADMIN","REPOSITOR")
              .anyRequest().authenticated())
              .addFilter(new JWTAuthenticationFilter(authManager()))
              .addFilter(new JWTAuthorizationFilter(authManager()))
              .csrf().disable()
              .cors().and()
              .sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
              .build();
    }
    
    //Implementacion de password encoder
    @Bean
    PasswordEncoder passwordEncoder(){
      return new BCryptPasswordEncoder();
    }
}
