package mySystem.QuickSales.DTO;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CtaCteDTO implements Serializable{
  private int id;
  private float saldo;
  private String estado;
}
