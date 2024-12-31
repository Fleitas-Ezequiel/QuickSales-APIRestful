package mySystem.QuickSales.DTO;

import java.io.Serializable;
import java.util.List;
import lombok.Data;

@Data
public class ClienteDTO implements Serializable{
  private int id;
  private String nombre;
  private String apellido;
  private Long telefono;
  private List<CtaCteDTO> cuenta_corriente_dto;
}
