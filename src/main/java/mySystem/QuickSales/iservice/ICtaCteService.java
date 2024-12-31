package mySystem.QuickSales.iservice;

import java.util.List;
import mySystem.QuickSales.DTO.CtaCteDTO;

public interface ICtaCteService {
  
  public void registrarCtaCte(CtaCteDTO ctacte);
  public void actualizarCtaCte(CtaCteDTO ctacte);
  public void eliminarCtaCte(CtaCteDTO ctacte);
  public List<CtaCteDTO> verCtaCte();
}
