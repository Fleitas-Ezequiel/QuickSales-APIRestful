package mySystem.QuickSales.iservice;

import java.util.List;
import java.util.Optional;
import mySystem.QuickSales.DTO.ProductoDTO;
import mySystem.QuickSales.model.Producto;

public interface IProductoService {
  public void registrarProducto(ProductoDTO producto);
  public void modificarProducto(ProductoDTO producto);
  public void eliminarProducto(ProductoDTO producto);
  public List<ProductoDTO> verProducto();
  public Optional<Producto> findProductoById(int id);
}
