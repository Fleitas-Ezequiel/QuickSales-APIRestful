package mySystem.QuickSales.iservice;

import java.util.List;
import mySystem.QuickSales.DTO.VentaDTO;

public interface IVentaService {
  public void registrarVenta(VentaDTO venta);
  public void modificarVenta(VentaDTO venta);
  public void eliminarVenta(VentaDTO venta);
  public List<VentaDTO> verVenta();
}
