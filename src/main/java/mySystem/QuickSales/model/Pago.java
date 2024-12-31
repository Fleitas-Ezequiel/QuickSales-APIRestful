package mySystem.QuickSales.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name = "pago")
public class Pago {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int id_pago;
  
  private Date fecha;
  private float importe;
  private String detalle;
  
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "id_comprobante", referencedColumnName = "id_comprobante")
  private Comprobante comprobante;
  
  @OneToMany(mappedBy = "pago", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<MetodoPago> metodo_pago = new ArrayList<>();
}
