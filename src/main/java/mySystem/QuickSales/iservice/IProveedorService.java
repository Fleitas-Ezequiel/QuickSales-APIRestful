package mySystem.QuickSales.iservice;

import java.util.List;
import mySystem.QuickSales.DTO.ProveedorDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IProveedorService {
  public void registrarProveedor(ProveedorDTO proveedor);
  public void modificarProveedor(ProveedorDTO proveedor);
  public void eliminarProveedor(ProveedorDTO proveedor);
  public List<ProveedorDTO> verProveedor();
  public List<ProveedorDTO> verProveedoresFiltrado(String filtrado);
  public Page paginarProveedores(Pageable pageable);
}