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
@Table(name = "contacto")
public class Contacto {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int id_contacto;
  
  private String tipo;
  private String valor;
  
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "id_proveedor", referencedColumnName = "id_proveedor")
  private Proveedor proveedor;
}
