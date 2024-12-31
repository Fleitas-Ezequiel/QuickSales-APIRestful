package mySystem.QuickSales.DTO;

import java.util.Date;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConsumoDTO {
    private int id;
    private Date fecha;
    private List<StockDTO> stock_dto;
}
