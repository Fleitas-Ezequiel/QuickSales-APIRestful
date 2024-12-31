package mySystem.QuickSales.service;

import mySystem.QuickSales.iservice.ICajaService;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import mySystem.QuickSales.DTO.CajaDTO;
import mySystem.QuickSales.model.Caja;
import mySystem.QuickSales.repository.CajaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CajaService implements ICajaService{
  
  @Autowired
  private CajaRepository caja_repo;

  @Autowired
  private ModelMapper modelMapper;
  
  @Autowired
  private DateTimeFormatter formato;
  

  private final DateTimeFormatter formato2 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
  
  @Override
  public void registrarCaja(CajaDTO caja) {
    try {
      String fecha_string;
      LocalDate fecha_caja = LocalDate.now();
      if(caja.getFecha_creacion() != null){
        fecha_string = formato2.format(caja.getFecha_creacion());
        fecha_caja = LocalDate.parse(fecha_string, formato2);
      } else {
        fecha_string = formato2.format(fecha_caja);
        fecha_caja = LocalDate.parse(fecha_string, formato2);
      }
      
      Optional<Caja> existe_caja = caja_repo.findByFechaTipo(fecha_caja, caja.getTipo());
      if(existe_caja.isEmpty()){
        Caja ca = modelMapper.map(caja, Caja.class);
        if(ca.getFecha_creacion() == null){
          LocalDate ahora = LocalDate.now();
          ca.setFecha_creacion(LocalDate.parse(formato.format(ahora), formato));
        }
        caja_repo.save(ca);
      }
    } catch (Exception e) {
      System.err.println(e.getMessage());
    }
  }

  @Override
  public void modificarCaja(CajaDTO caja_dto) {
    try {
      Optional<Caja> ca_optional = caja_repo.findById(caja_dto.getId());
      if(ca_optional.isPresent()){
        Caja caja = modelMapper.map(ca_optional.get(), Caja.class);
        caja_repo.save(caja);
      }
    } catch (Exception e) {
      System.err.println(e.getMessage());
    }
  }

  @Override
  public void eliminarCaja(CajaDTO caja) {
    try {
      Optional<Caja> ca = caja_repo.findById(caja.getId());
      if(ca.isPresent()){
        caja_repo.deleteById(ca.get().getId_caja());
      }
    } catch (Exception e) {
      System.err.println(e.getMessage());
    }
  }

  @Override
  public List<CajaDTO> verCajas() {
    List<CajaDTO> lista = new ArrayList();
    for(Caja c : caja_repo.findAll()){
      lista.add(modelMapper.map(c, CajaDTO.class));
    }
    return lista;
  }

}
