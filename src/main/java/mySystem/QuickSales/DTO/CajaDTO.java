package mySystem.QuickSales.DTO;

import java.io.Serializable;
import java.time.LocalDate;
import lombok.Data;

@Data
public class CajaDTO implements Serializable{
  private int id;
  private float cantidad;
  private String tipo;
  private LocalDate fecha_creacion;
}
