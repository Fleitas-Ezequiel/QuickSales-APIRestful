package mySystem.QuickSales.service;

import mySystem.QuickSales.iservice.IMetodoPagoService;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import mySystem.QuickSales.DTO.MetodoPagoDTO;
import mySystem.QuickSales.model.MetodoPago;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import mySystem.QuickSales.repository.MetodoPagoRepository;

@Service
public class MetodoPagoService implements IMetodoPagoService{

  @Autowired
  private MetodoPagoRepository metodo_pago_repo;
  
  @Autowired
  private ModelMapper modelMapper;

  @Override
  public void registrarMetodoPago(MetodoPagoDTO metodo) {
    try {
      MetodoPago mpp = modelMapper.map(metodo, MetodoPago.class);
      metodo_pago_repo.save(mpp);
    } catch (Exception e) {
      System.err.println(e.getMessage());
    }
  }

  @Override
  public void modificarMetodoPago(MetodoPagoDTO metodo) {
    try {
      Optional<MetodoPago> mpp = metodo_pago_repo.findById(metodo.getId());
      if(mpp.isPresent()){
        MetodoPago metP = modelMapper.map(mpp.get(), MetodoPago.class);
        metodo_pago_repo.save(metP);
      }
    } catch (Exception e) {
      System.err.println(e.getMessage());
    }
  }

  @Override
  public void eliminarMetodoPago(MetodoPagoDTO metodo) {
    try {
      Optional<MetodoPago> mpp = metodo_pago_repo.findById(metodo.getId());
      if(mpp.isPresent()){
        metodo_pago_repo.deleteById(mpp.get().getId_metodo_pago());
      }
    } catch (Exception e) {
      System.err.println(e.getMessage());
    }
  }

  @Override
  public List<MetodoPagoDTO> verMetodoPago() {
    List<MetodoPagoDTO> lista = new ArrayList();
    for(MetodoPago mpp : metodo_pago_repo.findAll()){
      lista.add(modelMapper.map(mpp, MetodoPagoDTO.class));
    }
    return lista;
  }
  
  
}
