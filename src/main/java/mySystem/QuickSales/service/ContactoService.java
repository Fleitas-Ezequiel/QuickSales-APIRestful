package mySystem.QuickSales.service;

import mySystem.QuickSales.iservice.IContactoService;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import mySystem.QuickSales.DTO.ContactoDTO;
import mySystem.QuickSales.model.Contacto;
import mySystem.QuickSales.repository.ContactoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContactoService implements IContactoService{

  @Autowired
  private ContactoRepository contactoRepo;

  @Autowired
  private ModelMapper modelMapper;
  
  @Override
  public void registrarContacto(ContactoDTO contacto_dto) {
    try {
      Contacto con = modelMapper.map(contacto_dto, Contacto.class);
      if(con.getTipo().equalsIgnoreCase("telefono") && con.getValor().equals("\\d+")){
          contactoRepo.save(con);
      } else if (con.getTipo().equalsIgnoreCase("email")){
          contactoRepo.save(con);
      } else {
          System.err.println("Error de registro");
      }
    } catch (Exception e) {
      System.out.println(Arrays.toString(e.getStackTrace()));
    }
  }

  @Override
  public void actualizarContacto(ContactoDTO contacto_dto) {
    try {
      Optional<Contacto> con = contactoRepo.findById(contacto_dto.getId());
      if(con.isPresent()){
        Contacto contacto = modelMapper.map(con.get(), Contacto.class);
        if(contacto.getTipo().equalsIgnoreCase("telefono") && contacto.getValor().equals("\\d+")){
          contactoRepo.save(contacto);
        } else if (contacto.getTipo().equalsIgnoreCase("email")){
            contactoRepo.save(contacto);
        } else {
            System.err.println("Error de registro");
        }
      }
    } catch (Exception e) {
      System.out.println(Arrays.toString(e.getStackTrace()));
    }
  }

  @Override
  public void eliminarContacto(ContactoDTO contacto_dto) {
    Optional<Contacto> con = contactoRepo.findById(contacto_dto.getId());
    if(con.isPresent()){
      contactoRepo.deleteById(con.get().getId_contacto());
    }
  }

  @Override
  public List<ContactoDTO> verContactos() {
    List<ContactoDTO> lista = new ArrayList();
    for(Contacto c : contactoRepo.findAll()){
      lista.add(modelMapper.map(c, ContactoDTO.class));
    }
    return lista;
  }
          
  
  @Override
  public List<ContactoDTO> listarPorProveedor(int id_proveedor){
    List<ContactoDTO> lista = new ArrayList();
    for(Contacto c : contactoRepo.findByProveedorId(id_proveedor)){
      lista.add(modelMapper.map(c, ContactoDTO.class));
    }
    return lista;
  }
}
