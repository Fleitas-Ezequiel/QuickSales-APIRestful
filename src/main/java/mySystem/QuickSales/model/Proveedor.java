package mySystem.QuickSales.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "proveedor")
public class Proveedor {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int id_proveedor;
  
  private String nombre;
  private Long cuit;
  
  @OneToMany(mappedBy = "proveedor", cascade = CascadeType.ALL)
  private List<Comprobante> comprobante = new ArrayList<>();
  
  @OneToMany(mappedBy = "proveedor", cascade = CascadeType.ALL)
  private List<Contacto> contacto = new ArrayList<>();
}
