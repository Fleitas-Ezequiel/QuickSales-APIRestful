package mySystem.QuickSales.iservice;

import java.util.List;
import java.util.Optional;
import mySystem.QuickSales.DTO.ComprobanteDTO;
import mySystem.QuickSales.model.Comprobante;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IComprobanteService {
  public void registrarComprobante(ComprobanteDTO comprobante);
  public void actualizarComprobante(ComprobanteDTO comprobante);
  public void eliminarComprobante(String id_comprobante);
  public List<ComprobanteDTO> verComprobantes();
  public Page paginarComprobantes(Pageable pageable);
  public Optional<Comprobante> findComprobanteById(String id_comprobante);
}
