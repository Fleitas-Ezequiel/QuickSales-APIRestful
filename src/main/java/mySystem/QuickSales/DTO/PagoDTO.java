package mySystem.QuickSales.DTO;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PagoDTO implements Serializable{
  private int id;
  private Date fecha;
  private float importe;
  private String detalle;
  private List<MetodoPagoDTO> metodo_pago_dto;
}
