package mySystem.QuickSales.service;

import mySystem.QuickSales.iservice.IPagoService;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import mySystem.QuickSales.DTO.PagoDTO;
import mySystem.QuickSales.model.Pago;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import mySystem.QuickSales.repository.PagoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Service
public class PagoService implements IPagoService{

  @Autowired
  private PagoRepository pago_proveedor_repo;
  
  @Autowired
  private ModelMapper modelMapper;

  @Override
  public void registrarPagoProveedor(PagoDTO pago_dto) {
    try {
      Pago pago = modelMapper.map(pago_dto, Pago.class);
     
      pago_proveedor_repo.save(pago);
    } catch (Exception e) {
      System.err.println(e.getMessage());
    }
  }

  @Override
  public void actualizrPagoProveedor(PagoDTO pago_dto) {
    try {
      Optional<Pago> pago = pago_proveedor_repo.findById(pago_dto.getId());
      if(pago.isPresent()){
        Pago pago_prov = modelMapper.map(pago, Pago.class);
        pago_proveedor_repo.save(pago_prov);
      }
    } catch (Exception e) {
      System.err.println(e.getMessage());
    }
  }

  @Override
  public void eliminarPagoProveedor(int id_pago) {
    try {
      Optional<Pago> pago = pago_proveedor_repo.findById(id_pago);
      if(pago.isPresent()){
        pago_proveedor_repo.deleteById(pago.get().getId_pago());
      }
    } catch (Exception e) {
      System.err.println(e.getMessage());
    }
  }

  @Override
  public List<PagoDTO> verPagoProveedor() {
    List<PagoDTO> lista = new ArrayList();
    for(Pago pago : pago_proveedor_repo.findAll()){
      lista.add(modelMapper.map(pago, PagoDTO.class));
    }
    return lista;
  }

  @Override
  public Page<PagoDTO> paginarPago(Pageable pageable) {
    Page<Pago> pagos = pago_proveedor_repo.findAll(pageable);
    return pagos.map( (pago)-> {
      PagoDTO pago_dto = modelMapper.map(pago, PagoDTO.class);
      return pago_dto;
    });
  }
  
  
  
}
