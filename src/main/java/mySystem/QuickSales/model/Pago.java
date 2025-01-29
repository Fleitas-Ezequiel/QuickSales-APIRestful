package mySystem.QuickSales.model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "pago")
public class Pago {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int id_pago;
  
  private Date fecha;
  private float importe;
  private String detalle;
  private String tipo;
  
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "id_comprobante", referencedColumnName = "id_comprobante")
  private Comprobante comprobante;
}
