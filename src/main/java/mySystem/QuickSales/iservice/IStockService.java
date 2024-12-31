package mySystem.QuickSales.iservice;

import java.util.List;
import mySystem.QuickSales.DTO.StockDTO;

public interface IStockService {
  
  public void registrarStock(StockDTO stock);
  public void actualizarStock(StockDTO stock);
  public void eliminarStock(StockDTO stock);
  public List<StockDTO> verStock();
}
