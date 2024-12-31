package mySystem.QuickSales.model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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
@Table(name = "stock")
public class Stock {
  @Id
  private String id_stock;
  
  private Long codigo;
  private Date fecha_vencimiento;
  private String estado;
  private float precio_venta;
  private float precio_compra;
  
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "id_producto", referencedColumnName = "id_producto")
  private Producto producto;
  
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "id_comprobante", referencedColumnName = "id_comprobante")
  private Comprobante comprobante;
  
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "id_venta", referencedColumnName = "id_venta")
  private Venta venta;
  
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "id_descuento", referencedColumnName = "id_descuento")
  private Descuento descuento;
  
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "id_consumo", referencedColumnName = "id_consumo")
  private Consumo consumo;
}
