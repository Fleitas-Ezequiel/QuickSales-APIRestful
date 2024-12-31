package mySystem.QuickSales.model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "metodo_venta")
public class MetodoVenta {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int id_metodo_venta;
  
  private String tipo;
  private float importe;
  
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "id_venta", referencedColumnName = "id_venta")
  private Venta venta;
  
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "id_caja", referencedColumnName = "id_caja")
  private Caja caja;
  
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "id_cuenta_corriente", referencedColumnName = "id_cuenta_corriente")
  private CtaCte cuenta_corriente;
}
