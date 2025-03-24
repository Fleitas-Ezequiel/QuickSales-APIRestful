package mySystem.QuickSales.iservice;

import java.util.List;
import java.util.Optional;
import mySystem.QuickSales.DTO.ProveedorDTO;
import mySystem.QuickSales.model.Proveedor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IProveedorService {
  public void registrarProveedor(ProveedorDTO proveedor);
  public void modificarProveedor(ProveedorDTO proveedor);
  public void eliminarProveedor(int id_proveedor);
  public List<ProveedorDTO> verProveedores();
  public ProveedorDTO verProveedorFiltrado(String filtrado);
  public Page paginarProveedores(Pageable pageable);
  public Optional<Proveedor> findByID(int id_proveedor);
}