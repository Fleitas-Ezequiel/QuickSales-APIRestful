package mySystem.QuickSales.service;

import mySystem.QuickSales.iservice.ICtaCteService;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import mySystem.QuickSales.DTO.CtaCteDTO;
import mySystem.QuickSales.model.CtaCte;
import mySystem.QuickSales.repository.CtaCteRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CtaCteService implements ICtaCteService{

  @Autowired
  private CtaCteRepository ctacte_repo;

  @Autowired
  private ModelMapper modelMapper;
  
  @Override
  public void registrarCtaCte(CtaCteDTO ctacte) {
    try {
      CtaCte cc = modelMapper.map(ctacte, CtaCte.class);
      ctacte_repo.save(cc);
    } catch (Exception e) {
      System.err.println(e.getMessage());
    }
  }

  @Override
  public void actualizarCtaCte(CtaCteDTO ctacte) {
    try {
      Optional<CtaCte> cc = ctacte_repo.findById(ctacte.getId());
      if(cc.isPresent()){
        CtaCte cuenta_c = modelMapper.map(cc.get(), CtaCte.class);
        ctacte_repo.save(cuenta_c);
      }
    } catch (Exception e) {
      System.err.println(e.getMessage());
    }
  }

  @Override
  public void eliminarCtaCte(CtaCteDTO ctacte) {
    try {
      Optional<CtaCte> cc = ctacte_repo.findById(ctacte.getId());
      if(cc.isPresent()){
        ctacte_repo.deleteById(cc.get().getId_cuenta_corriente());
      }
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }

  @Override
  public List<CtaCteDTO> verCtaCte() {
    List<CtaCteDTO> lista = new ArrayList();
    for(CtaCte c : ctacte_repo.findAll()){
      lista.add(modelMapper.map(c, CtaCteDTO.class));
    }
    return lista;
  }
  
  
  
}
