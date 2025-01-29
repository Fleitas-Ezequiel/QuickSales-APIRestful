package mySystem.QuickSales.iservice;

import java.util.List;
import mySystem.QuickSales.DTO.PagoDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IPagoService {
  
  public void registrarPagoProveedor(PagoDTO pago);
  public void actualizrPagoProveedor(PagoDTO pago);
  public void eliminarPagoProveedor(int id_pago);
  public List<PagoDTO> verPagoProveedor();
  public Page paginarPago(Pageable pageable);
}
