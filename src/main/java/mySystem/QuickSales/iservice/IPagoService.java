package mySystem.QuickSales.iservice;

import java.util.List;
import mySystem.QuickSales.DTO.PagoDTO;

public interface IPagoService {
  
  public void registrarPagoProveedor(PagoDTO pago);
  public void actualizrPagoProveedor(PagoDTO pago);
  public void eliminarPagoProveedor(PagoDTO pago);
  public List<PagoDTO> verPagoProveedor();
}
