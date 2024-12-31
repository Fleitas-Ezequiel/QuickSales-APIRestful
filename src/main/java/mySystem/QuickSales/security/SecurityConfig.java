package mySystem.QuickSales.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    
    private final UserDetailsService userDetailsService;  //Administrador de credenciales de usuario
    private final JWTAuthorizationFilter jwtAuthorizationFilter;
  
    @Bean
    //filtros de seguridad, se encarga de manejar las solicitudes de autenticación y autorización en la aplicación
    SecurityFilterChain filterChain(HttpSecurity http, AuthenticationManager authManager) throws Exception{
      JWTAuthenticationFilter jwtAuthenticationFilter = new JWTAuthenticationFilter();
      jwtAuthenticationFilter.setAuthenticationManager(authManager);
      jwtAuthenticationFilter.setFilterProcessesUrl("/login"); //Establecemos la ruta para el inicio de sesion

      return http
              .cors()
              .and()
              .csrf().disable()         //desabilitamos el  cross site request forgery
              .authorizeHttpRequests()      //reglas de solicitudes
              .requestMatchers("/user/**").permitAll() //habilitacion de registro/login de un usuario y permitimos cualquier solicitud que ingrese a la api
              .requestMatchers("/admin/**").hasRole("ROLE_ADMIN")
              .requestMatchers("/ventas/**").hasRole("ROLE_VENDEDOR")
              .requestMatchers("/deposito/**").hasRole("ROLE_REPOSITOR")
              .requestMatchers("/proveedores/**").hasRole("ROLE_COMPRADOR")
              .anyRequest()
              .authenticated()          //debe estar autenticada
              .and()
              .httpBasic()
              .and()                    //y ademas
              .sessionManagement()      //la gestion de sesiones
              .sessionCreationPolicy(SessionCreationPolicy.STATELESS)   //politica de gestion de sesiones STATELESSS, osea sin estado
              .and()      
              .addFilter(jwtAuthenticationFilter)  //agregamos un filtro
              .addFilterBefore(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class)
              .build();                 //construir el security filter chain
    }
  
    @Bean
    AuthenticationManager authnManager(HttpSecurity http) throws Exception{
      return http.getSharedObject(AuthenticationManagerBuilder.class)
              .userDetailsService(userDetailsService)
              .passwordEncoder(passwordEncoder())
              .and()
              .build();
    }
    
    //Implementacion de password encoder
    @Bean
    PasswordEncoder passwordEncoder(){
      return new BCryptPasswordEncoder();
    }
}
