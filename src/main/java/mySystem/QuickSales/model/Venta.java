package mySystem.QuickSales.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "venta")
public class Venta {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int id_venta;
  
  private Date fecha;
  private float importe;
  private String detalle;
  private int cantidad;
  private String unidad;
  
  @OneToMany(mappedBy = "venta", cascade = CascadeType.ALL)
  private List<MetodoVenta> metodo_venta = new ArrayList<>();
  
  @OneToMany(mappedBy = "venta", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Stock> stock = new ArrayList<>();
}
