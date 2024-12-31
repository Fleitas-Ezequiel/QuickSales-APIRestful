package mySystem.QuickSales.DTO;

import java.io.Serializable;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RetiroDTO implements Serializable{
  private int id;
  private Date fecha;
  private String motivo;
  private float importe;
  private String detalle;
}
