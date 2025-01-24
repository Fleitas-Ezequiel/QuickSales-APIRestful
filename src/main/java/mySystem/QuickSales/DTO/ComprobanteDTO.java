package mySystem.QuickSales.DTO;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ComprobanteDTO {
  private String id;
  private String tipo;
  private LocalDate fecha_emision;
  private float importe;
  private String estado;
  private ProveedorDTO proveedor_dto;
}
