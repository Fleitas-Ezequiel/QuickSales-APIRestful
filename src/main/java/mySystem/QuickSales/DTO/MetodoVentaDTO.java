package mySystem.QuickSales.DTO;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MetodoVentaDTO implements Serializable{
  private int id;
  private String tipo;
  private float importe;
  private VentaDTO venta_dto;
  private CajaDTO caja_dto;
  private CtaCteDTO ctacte_dto;
}
