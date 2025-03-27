package mySystem.QuickSales.DTO;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StockDTOControl implements Serializable{
    private String producto;
    private String marca;
    private String medida;
    private String descripcion;
    private Long cantidad;
}
