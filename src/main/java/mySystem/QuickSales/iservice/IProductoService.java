package mySystem.QuickSales.iservice;

import java.util.List;
import java.util.Optional;
import mySystem.QuickSales.DTO.ProductoDTO;
import mySystem.QuickSales.model.Producto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IProductoService {
  public void registrarProducto(ProductoDTO producto);
  public void modificarProducto(ProductoDTO producto);
  public void eliminarProducto(int id_producto);
  public List<ProductoDTO> verProducto();
  public Optional<Producto> findProductoById(int id);
  public Page<ProductoDTO> paginarProductos(Pageable pageable);
  public ProductoDTO verProveedorFiltrado(String producto);
}
