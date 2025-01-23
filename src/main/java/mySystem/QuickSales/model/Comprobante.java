package mySystem.QuickSales.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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
@Table(name = "comprobante")
public class Comprobante {
  @Id
  @Column(
    name = "id_comprobante",
    length = 30,
    nullable = false
  )
  private String id;
  
  private String tipo;
  private Date fecha_emision;
  private float importe;
  private String estado;
  
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "id_proveedor", referencedColumnName = "id_proveedor")
  private Proveedor proveedor;
  
  @OneToMany(mappedBy = "comprobante", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Pago> pago = new ArrayList<>();
  
  @OneToMany(mappedBy = "comprobante", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Stock> stock = new ArrayList<>();
}
