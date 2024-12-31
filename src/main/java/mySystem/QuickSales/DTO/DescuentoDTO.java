package mySystem.QuickSales.DTO;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DescuentoDTO implements Serializable{
  private int id_descuento;
  private float importe;
}
