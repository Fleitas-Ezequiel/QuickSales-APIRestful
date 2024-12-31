package mySystem.QuickSales.iservice;

import java.util.List;
import mySystem.QuickSales.DTO.RetiroDTO;

public interface IRetiroService {
  public void registrarRetiro(RetiroDTO retiro);
  public void modificarRetiro(RetiroDTO retiro);
  public void eliminarRetiro(RetiroDTO retiro);
  public List<RetiroDTO> verRetiro();
}
