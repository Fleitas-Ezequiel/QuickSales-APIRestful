package mySystem.QuickSales.configuration;

import java.time.format.DateTimeFormatter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FormatoFecha {
  
  @Bean
  public DateTimeFormatter formatoPersonalizado(){
    final DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    return formato;
  }
}
