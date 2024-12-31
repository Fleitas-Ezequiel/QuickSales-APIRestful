package mySystem.QuickSales.service;

import mySystem.QuickSales.iservice.IPagoService;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import mySystem.QuickSales.DTO.MetodoPagoDTO;
import mySystem.QuickSales.DTO.PagoDTO;
import mySystem.QuickSales.model.Pago;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import mySystem.QuickSales.repository.PagoRepository;

@Service
public class PagoService implements IPagoService{

  @Autowired
  private PagoRepository pago_proveedor_repo;
  
  @Autowired
  private MetodoPagoService metodo_pago_service;
  
  @Autowired
  private ModelMapper modelMapper;

  @Override
  public void registrarPagoProveedor(PagoDTO pago_dto) {
    try {
      Pago pp = modelMapper.map(pago_dto, Pago.class);
      pago_proveedor_repo.save(pp);
      if(!pago_dto.getMetodo_pago_dto().isEmpty()){
          for(MetodoPagoDTO mp: pago_dto.getMetodo_pago_dto() ){
              metodo_pago_service.registrarMetodoPago(mp);
          }
      }
    } catch (Exception e) {
      System.err.println(e.getMessage());
    }
  }

  @Override
  public void actualizrPagoProveedor(PagoDTO pago_dto) {
    try {
      Optional<Pago> pp = pago_proveedor_repo.findById(pago_dto.getId());
      if(pp.isPresent()){
        Pago pago_prov = modelMapper.map(pp, Pago.class);
        pago_proveedor_repo.save(pago_prov);
      }
    } catch (Exception e) {
      System.err.println(e.getMessage());
    }
  }

  @Override
  public void eliminarPagoProveedor(PagoDTO pago_dto) {
    try {
      Optional<Pago> pp = pago_proveedor_repo.findById(pago_dto.getId());
      if(pp.isPresent()){
        pago_proveedor_repo.deleteById(pp.get().getId_pago());
      }
    } catch (Exception e) {
      System.err.println(e.getMessage());
    }
  }

  @Override
  public List<PagoDTO> verPagoProveedor() {
    List<PagoDTO> lista = new ArrayList();
    for(Pago pp : pago_proveedor_repo.findAll()){
      lista.add(modelMapper.map(pp, PagoDTO.class));
    }
    return lista;
  }
  
  
  
}
