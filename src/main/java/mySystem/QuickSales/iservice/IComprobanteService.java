package mySystem.QuickSales.iservice;

import java.util.List;
import mySystem.QuickSales.DTO.ComprobanteDTO;

public interface IComprobanteService {
  public void registrarComprobante(ComprobanteDTO boleta);
  public void actualizarComprobante(ComprobanteDTO boleta);
  public void eliminarComprobante(ComprobanteDTO boleta);
  public List<ComprobanteDTO> verComprobantes();
}
