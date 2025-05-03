package mySystem.QuickSales.DTO;

import java.io.Serializable;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StockDTOControl implements Serializable{
    private int idProducto;
    private String producto;
    private String marca;
    private String medida;
    private String tipo;
    private String descripcion;
    private Long cantidad;
    private Float precio_venta;
    private Date fecha_vencimiento;
    private Long codigo;
}
