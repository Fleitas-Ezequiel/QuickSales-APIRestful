package mySystem.QuickSales.service;

import mySystem.QuickSales.iservice.IProveedorService;
import mySystem.QuickSales.iservice.IContactoService;
import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import mySystem.QuickSales.DTO.ContactoDTO;
import mySystem.QuickSales.DTO.ProveedorDTO;
import mySystem.QuickSales.model.Contacto;
import mySystem.QuickSales.model.Proveedor;
import mySystem.QuickSales.repository.ProveedorRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProveedorService implements IProveedorService{

  @Autowired
  private ProveedorRepository proveedorRepo;
  
  @Autowired
  private IContactoService contactoService;
  
  @Autowired
  private ModelMapper modelMapper;

  @Transactional
  @Override
  public void registrarProveedor(ProveedorDTO proveedor_dto) {
    try {
      Proveedor proveedor = modelMapper.map(proveedor_dto, Proveedor.class);
      List<Contacto> contacto_list = new ArrayList<>();
      if(!proveedor_dto.getContacto_dto().isEmpty() || proveedor_dto.getContacto_dto()!= null){
        for(ContactoDTO c_dto: proveedor_dto.getContacto_dto()){
          Contacto c = modelMapper.map(c_dto, Contacto.class);
          c.setProveedor(proveedor);
          contacto_list.add(c);
        }
      }
      proveedor.setContacto(contacto_list);
      proveedorRepo.save(proveedor);
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }

  @Transactional
  @Override
  public void modificarProveedor(ProveedorDTO proveedor_dto) {
    try {
      Optional<Proveedor> prov = proveedorRepo.findById(proveedor_dto.getId());
      if(prov.isPresent()){
        Proveedor p = modelMapper.map(proveedor_dto, Proveedor.class);

        for(ContactoDTO c : proveedor_dto.getContacto_dto()){
            contactoService.actualizarContacto(c);
        }
        proveedorRepo.save(p);
      }
    } catch (Exception e) {
      System.err.println("Error del lado del service \n"+e.getMessage());
    }
  }
  
  @Transactional
  @Override
  public void eliminarProveedor(int id_proveedor) {
    try {
      Optional<Proveedor> prov = proveedorRepo.findById(id_proveedor);
      if(prov.isPresent()){
        proveedorRepo.deleteById(id_proveedor);
      }
    } catch (Exception e) {
      System.err.println(e.getMessage());
    }
  }

  @Transactional
  @Override
  public List<ProveedorDTO> verProveedor() {
    List<Proveedor> proveedores = proveedorRepo.findAll();
    List<ProveedorDTO> lista = new ArrayList();
    for(Proveedor proveedor : proveedores){
      ProveedorDTO dto = modelMapper.map(proveedor, ProveedorDTO.class);
      List<ContactoDTO> contactos = contactoService.listarPorProveedor(proveedor.getId_proveedor());
      dto.setContacto_dto(contactos);
      lista.add(dto);
    }
    return lista;
  }

  @Override
  public List<ProveedorDTO> verProveedoresFiltrado(String filtrado) {
    List<Proveedor> proveedores = proveedorRepo.findAll();
    List<ProveedorDTO> lista = new ArrayList();
    for(Proveedor proveedor : proveedores){
      ProveedorDTO dto = modelMapper.map(proveedor, ProveedorDTO.class);
      List<ContactoDTO> contactos = contactoService.listarPorProveedor(proveedor.getId_proveedor());
      dto.setContacto_dto(contactos);
      lista.add(dto);
    }
    return lista;
  }

    @Override
    public Page<ProveedorDTO> paginarProveedores(Pageable pageable) {
        Page<Proveedor> proveedor = proveedorRepo.findAll(pageable);
        return proveedor.map((provider)-> {
            ProveedorDTO proveedor_dto = modelMapper.map(provider, ProveedorDTO.class);
            if(!provider.getContacto().isEmpty()){
                List<ContactoDTO> lista_contacto = new ArrayList();
                for(Contacto c: provider.getContacto()){
                    ContactoDTO contacto_dto = modelMapper.map(c, ContactoDTO.class);
                    lista_contacto.add(contacto_dto);
                }
                proveedor_dto.setContacto_dto(lista_contacto);
            }
            return proveedor_dto;
        });
        
    }

  
  
}
