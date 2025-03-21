package mySystem.QuickSales.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {
  
  @Bean
  public WebMvcConfigurer corsConfigurer(){
    return new WebMvcConfigurer() {
      @Override
      public void addCorsMappings(CorsRegistry registry){
        registry.addMapping("/login")//Mapeamos la ruta que nos permite logearnos
                .allowedOrigins("*")
                .allowedMethods("POST")
                .exposedHeaders("*");
        
        registry.addMapping("/**")//Mapeamos la ruta que nos devuelve un listado de personas
                .allowedOrigins("http://localhost:3000")
                .allowedMethods("*");
      }
    };
  }
}
