package mySystem.QuickSales.DTO;

import java.time.LocalDate;
import lombok.Data;

@Data
public class ComprobanteDTO {
  private String id;
  private String tipo;
  private LocalDate fecha;
  private float importe;
  private String estado;
  private ProveedorDTO proveedor_dto;
}
