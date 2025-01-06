package mySystem.QuickSales.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {
    
//    private final UserDetailsService userDetailsService;  //Administrador de credenciales de usuario
//    private final JWTAuthorizationFilter jwtAuthorizationFilter;
    
    @Autowired
    private AuthenticationConfiguration authenticationConfiguration;
    
    @Bean
    AuthenticationManager authManager() throws Exception{
      return authenticationConfiguration.getAuthenticationManager();
    }
  
    @Bean
    //filtros de seguridad, se encarga de manejar las solicitudes de autenticación y autorización en la aplicación
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
//      JWTAuthenticationFilter jwtAuthenticationFilter = new JWTAuthenticationFilter(authManager());
//      jwtAuthenticationFilter.setAuthenticationManager(authManager());
//      jwtAuthenticationFilter.setFilterProcessesUrl("/login"); //Establecemos la ruta para el inicio de sesion

      return http.authorizeHttpRequests( (authz) -> authz
              .requestMatchers("/user/list").permitAll()
              .requestMatchers(HttpMethod.POST,"/admin").hasRole("ADMIN")
              .anyRequest().authenticated())
              .addFilter(new JWTAuthenticationFilter(authManager()))
              .addFilter(new JWTAuthorizationFilter(authManager()))
              .csrf().disable()
              .cors().and()
              .sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
              .build();
      
//      return http
//              .cors()
//              .and()
//              .csrf().disable()         //desabilitamos el  cross site request forgery
//              .authorizeHttpRequests()      //reglas de solicitudes
//              .requestMatchers("/user/**").permitAll() //habilitacion de registro/login de un usuario y permitimos cualquier solicitud que ingrese a la api
//              .requestMatchers("/admin/**").hasRole("ADMIN")
//              .requestMatchers("/ventas/**").hasRole("VENDEDOR")
//              .requestMatchers("/deposito/**").hasRole("REPOSITOR")
//              .requestMatchers("/proveedores/**").hasRole("COMPRADOR")
//              .anyRequest()
//              .authenticated()          //debe estar autenticada
//              .and()
//              .httpBasic()
//              .and()                    //y ademas
//              .sessionManagement()      //la gestion de sesiones
//              .sessionCreationPolicy(SessionCreationPolicy.STATELESS)   //politica de gestion de sesiones STATELESSS, osea sin estado
//              .and()      
//              .addFilter(new JWTAuthenticationFilter(authManager()))  //agregamos un filtro
////              .addFilterBefore(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class)
//              .build();                 //construir el security filter chain
    }
    
    //Implementacion de password encoder
    @Bean
    PasswordEncoder passwordEncoder(){
      return new BCryptPasswordEncoder();
    }
}
