package mySystem.QuickSales.iservice;

import java.util.List;
import mySystem.QuickSales.DTO.StockDTO;
import mySystem.QuickSales.DTO.StockDTOControl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IStockService {
  
  public void registrarStock(StockDTO stock);
  public void actualizarStock(StockDTO stock);
  public void eliminarStock(StockDTO stock);
  public List<StockDTO> verStock();
  public List<StockDTOControl> listarStocks();
  public Page paginarStock(Pageable pageable);
  public Page paginarStockPorProducto(Pageable pageable, int id_producto, String estado);
  public List<StockDTOControl> listarStockPorProducto(String producto);
}
