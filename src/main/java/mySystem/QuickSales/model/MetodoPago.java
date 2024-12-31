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
@Table(name = "metodo_pago_proveedor")
public class MetodoPago {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int id_metodo_pago;
  
  private String tipo;
  private float importe;
  
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "id_pago", referencedColumnName = "id_pago")
  private Pago pago;
   
}
