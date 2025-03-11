package mySystem.QuickSales.DTO;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StockDTOControl implements Serializable{
    private String marca;
    private String descripcion;
    private String medida;
    private int cantidad;
}
