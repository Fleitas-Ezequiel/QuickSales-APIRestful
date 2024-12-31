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
@Table(name = "retiro")
public class Retiro {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int id_retiro;
  
  private float importe;
  private Date fecha;
  private String motivo;
  private String detalle;
  
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "id_caja", referencedColumnName = "id_caja")
  private Caja caja;
}
