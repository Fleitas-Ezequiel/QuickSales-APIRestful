package mySystem.QuickSales.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "cliente")
public class Cliente {
  @Id
  @Column(name = "id_cliente",length = 30)
  private int id_cliente;
  
  private String nombre;
  private String apellido;
  private Long telefono;
  
  @OneToOne(mappedBy = "cliente", cascade = CascadeType.ALL)
  private CtaCte ctacte;
 
}
