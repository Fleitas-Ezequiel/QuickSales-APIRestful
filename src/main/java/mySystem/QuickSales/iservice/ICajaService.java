package mySystem.QuickSales.iservice;

import java.util.List;
import mySystem.QuickSales.DTO.CajaDTO;

public interface ICajaService {
  
  public void registrarCaja(CajaDTO caja);
  public void modificarCaja(CajaDTO caja);
  public void eliminarCaja(CajaDTO caja);
  public List<CajaDTO> verCajas();
}
