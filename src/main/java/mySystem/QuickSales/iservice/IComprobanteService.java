package mySystem.QuickSales.iservice;

import java.util.List;
import mySystem.QuickSales.DTO.ComprobanteDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IComprobanteService {
  public void registrarComprobante(ComprobanteDTO boleta);
  public void actualizarComprobante(ComprobanteDTO boleta);
  public void eliminarComprobante(ComprobanteDTO boleta);
  public List<ComprobanteDTO> verComprobantes();
  public Page<ComprobanteDTO> paginarComprobantes(Pageable pageable);
}
