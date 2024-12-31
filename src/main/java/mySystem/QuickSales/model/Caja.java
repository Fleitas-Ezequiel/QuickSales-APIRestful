package mySystem.QuickSales.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "caja")
public class Caja {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int id_caja;
  
  private float cantidad;
  private String tipo;
  private LocalDate fecha_creacion;
  
  @OneToMany(mappedBy = "caja", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Retiro> retiro = new ArrayList<>();
  
  @OneToMany(mappedBy = "caja", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<MetodoVenta> metodo_venta = new ArrayList<>();
  
}
