package mySystem.QuickSales.iservice;

import java.util.List;
import mySystem.QuickSales.DTO.ProductoDTO;

public interface IProductoService {
  public void registrarProducto(ProductoDTO producto);
  public void modificarProducto(ProductoDTO producto);
  public void eliminarProducto(ProductoDTO producto);
  public List<ProductoDTO> verProducto();
}
