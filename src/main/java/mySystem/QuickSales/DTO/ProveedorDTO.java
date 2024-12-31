package mySystem.QuickSales.DTO;

import java.io.Serializable;
import java.util.List;
import lombok.Data;

@Data
public class ProveedorDTO implements Serializable{
  private int id;
  private String nombre;
  private Long cuit;
  private List<ContactoDTO> contacto_dto;
}
