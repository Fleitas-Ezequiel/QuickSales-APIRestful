package mySystem.QuickSales.DTO;

import java.io.Serializable;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProveedorDTO implements Serializable{
  private int id;
  private String nombre;
  private Long cuit;
  private List<ContactoDTO> contacto_dto;
}
