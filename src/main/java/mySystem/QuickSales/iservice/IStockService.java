package mySystem.QuickSales.iservice;

import java.util.List;
import mySystem.QuickSales.DTO.StockDTO;
import mySystem.QuickSales.DTO.StockDTOControl;

public interface IStockService {
  
  public void registrarStock(StockDTO stock);
  public void actualizarStock(StockDTO stock);
  public void eliminarStock(StockDTO stock);
  public List<StockDTO> verStock();
  public List<StockDTOControl> listarStocks();
}
