package mySystem.QuickSales.DTO;

import java.io.Serializable;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StockDTO implements Serializable{
  private String idStock;
  private Long codigo;
  private Date fecha_vencimiento;
  private String estado;
  private float precio_venta;
  private float precio_compra;
  private int cantidad;
  private ProductoDTO producto_dto;
  private ComprobanteDTO comprobante_dto;
  private VentaDTO venta_dto;
  private DescuentoDTO descuento_dto;
  private ConsumoDTO consumo_dto;
}
