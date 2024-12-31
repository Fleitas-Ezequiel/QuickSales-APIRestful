package mySystem.QuickSales.service;

import mySystem.QuickSales.iservice.IRetiroService;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import mySystem.QuickSales.DTO.RetiroDTO;
import mySystem.QuickSales.model.Retiro;
import mySystem.QuickSales.repository.RetiroRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RetiroService implements IRetiroService{

  @Autowired
  private RetiroRepository retiro_repo;

  @Autowired
  private ModelMapper modelMapper;
  
  @Override
  public void registrarRetiro(RetiroDTO retiro) {
    try {
      Retiro r = modelMapper.map(retiro, Retiro.class);
      retiro_repo.save(r);
    } catch (Exception e) {
      System.err.println(e.getMessage());
    }
  }

  @Override
  public void modificarRetiro(RetiroDTO retiro) {
    try {
      Optional<Retiro> r = retiro_repo.findById(retiro.getId());
      if(r.isPresent()){
        Retiro re = r.get();
        re = modelMapper.map(retiro, Retiro.class);
        retiro_repo.save(re);
      }
    } catch (Exception e) {
      System.err.println(e.getMessage());
    }
  }

  @Override
  public void eliminarRetiro(RetiroDTO retiro) {
    try {
      Optional<Retiro> r = retiro_repo.findById(retiro.getId());
      if(r.isPresent()){
        retiro_repo.deleteById(r.get().getId_retiro());
      }
    } catch (Exception e) {
      System.err.println(e.getMessage());
    }
  }

  @Override
  public List<RetiroDTO> verRetiro() {
    List<RetiroDTO> lista = new ArrayList();
    for(Retiro r : retiro_repo.findAll()){
      lista.add(modelMapper.map(r, RetiroDTO.class));
    }
    return lista;
  }
  
  
}
