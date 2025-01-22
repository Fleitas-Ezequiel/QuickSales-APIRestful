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
      Contacto contacto = new Contacto();
      contacto.setTipo(contacto_dto.getTipo());
      contacto.setValor(contacto_dto.getValor());
      contacto.setProveedor(contacto_dto.getProveedor());
      if(contacto.getTipo().equalsIgnoreCase("telefono") && contacto.getValor().matches("\\d+")){
          contactoRepo.save(contacto);
          System.out.println("Registro de telefono exitoso");
      } else if (contacto.getTipo().equalsIgnoreCase("email")){
          contactoRepo.save(contacto);
          System.out.println("Registro de email exitoso");
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
      Optional<Contacto> contacto_optional = contactoRepo.findById(contacto_dto.getId());
      if(contacto_optional.isPresent()){
        Contacto contacto = contacto_optional.get();
        contacto.setId_contacto(contacto_dto.getId());
        contacto.setTipo(contacto_dto.getTipo());
        contacto.setValor(contacto_dto.getValor());
        
        if(contacto.getTipo().equalsIgnoreCase("telefono") && contacto.getValor().matches("\\d+")){
//          int numero_telefono = Integer.parseInt(contacto.getValor());
          contactoRepo.save(contacto);
        } else if (contacto.getTipo().equalsIgnoreCase("email")){
            contactoRepo.save(contacto);
        } else {
            System.err.println("Error de actualizacion");
        }
      }
    } catch (Exception e) {
      System.out.println(Arrays.toString(e.getStackTrace()));
    }
  }

  @Override
  public void eliminarContacto(int id_contacto) {
    Optional<Contacto> con = contactoRepo.findById(id_contacto);
    if(con.isPresent()){
      contactoRepo.deleteById(con.get().getId_contacto());
    } else {
      System.out.println("No se encontro el contacto con id "+id_contacto);
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

    @Override
    public Optional<Contacto> findById(int id_contacto) {
      Optional<Contacto> contacto = contactoRepo.findById(id_contacto);
      if(contacto.isEmpty()){
        System.err.println("No se encontro el contacto con el id "+id_contacto);
      }
      return contacto;
    }
}
