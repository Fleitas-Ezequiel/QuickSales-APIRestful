package mySystem.QuickSales.iservice;

import java.util.List;
import mySystem.QuickSales.DTO.ProveedorDTO;

public interface IProveedorService {
  public void registrarProveedor(ProveedorDTO proveedor);
  public void modificarProveedor(ProveedorDTO proveedor);
  public void eliminarProveedor(ProveedorDTO proveedor);
  public List<ProveedorDTO> verProveedor();
  public List<ProveedorDTO> verProveedoresFiltrado(String filtrado);
}