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
@Table(name = "producto")
public class Producto {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int id_producto;
  
  private String producto;
  private String tipo;
  private String marca;
  private String medida;
  private String descripcion;
  
  @OneToMany(mappedBy = "producto", cascade = CascadeType.ALL)
  private List<Stock> stock = new ArrayList<>();
    
}
