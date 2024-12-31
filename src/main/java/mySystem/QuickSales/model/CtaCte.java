package mySystem.QuickSales.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "cuenta_corriente")
public class CtaCte {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int id_cuenta_corriente;
  
  private float saldo;
  private String estado;
  
  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "id_cliente", referencedColumnName = "id_cliente")
  private Cliente cliente;
  
  @OneToMany(mappedBy = "cuenta_corriente", cascade = CascadeType.ALL)
  private List<MetodoVenta> metodo_venta;
}
