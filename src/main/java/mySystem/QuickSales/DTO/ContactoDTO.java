package mySystem.QuickSales.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mySystem.QuickSales.model.Proveedor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContactoDTO implements Serializable{
  private int id;
  private String tipo;
  private String valor;
  
  @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
  private Proveedor proveedor;
}
