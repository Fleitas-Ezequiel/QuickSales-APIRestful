package mySystem.QuickSales.service;

import mySystem.QuickSales.iservice.IComprobanteService;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import mySystem.QuickSales.DTO.ComprobanteDTO;
import mySystem.QuickSales.model.Comprobante;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import mySystem.QuickSales.repository.ComprobanteRepository;

@Service
public class ComprobanteService implements IComprobanteService{
  
  @Autowired
  private ComprobanteRepository boleta_repo;

  @Autowired
  private ModelMapper modelMapper;
  
  @Override
  public void registrarComprobante(ComprobanteDTO comprobante_dto) {
    try {
      Comprobante comprobante = modelMapper.map(comprobante_dto, Comprobante.class);
      boleta_repo.save(comprobante);
    } catch (Exception e) {
      System.err.println(e.getMessage());
    }
  }

  @Override
  public void actualizarComprobante(ComprobanteDTO comprobante_dto) {
    try {
      Optional<Comprobante> comprobante = boleta_repo.findById(comprobante_dto.getId());
      if(comprobante.isPresent()){
        Comprobante boleta_proveedor = modelMapper.map(comprobante_dto, Comprobante.class);
        boleta_repo.save(boleta_proveedor);
      }
    } catch (Exception e) {
      System.err.println(e.getMessage());
    }
  }

  @Override
  public void eliminarComprobante(ComprobanteDTO comprobante_dto) {
    try {
      Optional<Comprobante> comprobante = boleta_repo.findById(comprobante_dto.getId());
      if(comprobante.isPresent()){
        boleta_repo.deleteById(comprobante.get().getId());
      }
    } catch (Exception e) {
      System.err.println(e.getMessage());
    }
  }

  @Override
  public List<ComprobanteDTO> verComprobantes() {
    List<ComprobanteDTO> lista = new ArrayList();
    for(Comprobante c : boleta_repo.findAll()){
      lista.add(modelMapper.map(c, ComprobanteDTO.class));
    }
    return lista;
  }
}
