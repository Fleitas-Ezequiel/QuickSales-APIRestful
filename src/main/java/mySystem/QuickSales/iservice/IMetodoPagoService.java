package mySystem.QuickSales.iservice;

import java.util.List;
import mySystem.QuickSales.DTO.MetodoPagoDTO;

public interface IMetodoPagoService {
  
  public void registrarMetodoPago(MetodoPagoDTO metodo);
  public void modificarMetodoPago(MetodoPagoDTO metodo);
  public void eliminarMetodoPago(MetodoPagoDTO metodo);
  public List<MetodoPagoDTO> verMetodoPago();
}
