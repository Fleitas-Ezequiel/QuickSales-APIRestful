package mySystem.QuickSales.service;

import mySystem.QuickSales.iservice.IMetodoVentaService;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import mySystem.QuickSales.DTO.MetodoVentaDTO;
import mySystem.QuickSales.model.MetodoVenta;
import mySystem.QuickSales.repository.MetodoVentaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MetodoVentaService implements IMetodoVentaService{

  @Autowired
  private MetodoVentaRepository metodo_venta_repo;
  
  @Autowired
  private ModelMapper modelMapper;

  @Override
  public void registrarMetodoVenta(MetodoVentaDTO metodo) {
    try {
      MetodoVenta mv = modelMapper.map(metodo, MetodoVenta.class);
      metodo_venta_repo.save(mv);
    } catch (Exception e) {
      System.err.println(e.getMessage());
    }
  }

  @Override
  public void actualizarMetodoVenta(MetodoVentaDTO metodo) {
    try {
      Optional<MetodoVenta> mv = metodo_venta_repo.findById(metodo.getId());
      if(mv.isPresent()){
        MetodoVenta m_venta = modelMapper.map(mv.get(), MetodoVenta.class);
        metodo_venta_repo.save(m_venta);
      }
    } catch (Exception e) {
      System.err.println(e.getMessage());
    }
  }

  @Override
  public void eliminarMetodoVenta(MetodoVentaDTO metodo) {
    try {
      Optional<MetodoVenta> mv = metodo_venta_repo.findById(metodo.getId());
      if(mv.isPresent()){
        metodo_venta_repo.deleteById(mv.get().getId_metodo_venta());
      }
    } catch (Exception e) {
      System.err.println(e.getMessage());
    }
  }

  @Override
  public List<MetodoVentaDTO> verMetodoVenta() {
    List<MetodoVentaDTO> lista = new ArrayList();
    for(MetodoVenta mv : metodo_venta_repo.findAll()){
      lista.add(modelMapper.map(mv, MetodoVentaDTO.class));
    }
    return lista;
  }
  
}
