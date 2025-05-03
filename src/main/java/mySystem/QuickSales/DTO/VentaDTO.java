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
public class VentaDTO implements Serializable{
  private int id;
  private Date fecha_venta;
  private float importe;
  private String detalle;
  private List<StockDTO> stock_dto;
  private List<MetodoVentaDTO> metodo_venta_dto;
}
