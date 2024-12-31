package mySystem.QuickSales.iservice;

import java.util.List;
import mySystem.QuickSales.DTO.MetodoVentaDTO;

public interface IMetodoVentaService {
  
  public void registrarMetodoVenta(MetodoVentaDTO metodo);
  public void actualizarMetodoVenta(MetodoVentaDTO metodo);
  public void eliminarMetodoVenta(MetodoVentaDTO metodo);
  public List<MetodoVentaDTO> verMetodoVenta();
}
