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
      Optional<Proveedor> proveedor_optional = proveedorRepo.findById(proveedor_dto.getId());
      if(proveedor_optional.isPresent()){
        Proveedor proveedor = proveedor_optional.get();
        proveedor.setNombre(proveedor_dto.getNombre());
        proveedor.setCuit(proveedor_dto.getCuit());
        
        eliminarContacto(proveedor.getContacto(), proveedor_dto.getContacto_dto());
        
        List<Contacto> contacto_list = new ArrayList();
        proveedor.setContacto(contacto_list);
        for(ContactoDTO contacto_dto: proveedor_dto.getContacto_dto()){
          Contacto contacto = new Contacto();
          if(!contacto_dto.getValor().isBlank() && !contacto_dto.getValor().isEmpty()){
            contacto.setId_contacto(contacto_dto.getId());
            contacto.setTipo(contacto_dto.getTipo());
            contacto.setValor(contacto_dto.getValor());
            contacto.setProveedor(proveedor);
            
            contacto_list.add(contacto);
          } else if(contacto_dto.getValor().isBlank()){
            contactoService.eliminarContacto(contacto_dto.getId());
          }
        }
        proveedorRepo.save(proveedor);
      }
    } catch (Exception e) {
      System.err.println("Error del lado del service \n"+e.getMessage());
    }
  }
  
  private void eliminarContacto(List<Contacto> contacto_list, List<ContactoDTO> contacto_dto_list){
    for(Contacto contacto: contacto_list){
      if(!contactoExiste(contacto, contacto_dto_list)){
        contactoService.eliminarContacto(contacto.getId_contacto());
      }
    }
  }
  
  private boolean contactoExiste(Contacto contacto, List<ContactoDTO> array){
    for(ContactoDTO contacto_dto: array){
      if(contacto.getId_contacto() == contacto_dto.getId()){
        return true;
      }
    }
    return false;
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
  public List<ProveedorDTO> verProveedores() {
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
  public ProveedorDTO verProveedorFiltrado(String filtrado) {
    List<Proveedor> proveedores = proveedorRepo.findByFiltros(filtrado);
    ProveedorDTO proveedor_dto = new ProveedorDTO();
    for(Proveedor proveedor : proveedores){
      ProveedorDTO dto = modelMapper.map(proveedor, ProveedorDTO.class);
      List<ContactoDTO> contactos = contactoService.listarPorProveedor(proveedor.getId_proveedor());
      dto.setContacto_dto(contactos);
      proveedor_dto = dto;
    }
    return proveedor_dto;
  }

    @Override
    public Page<ProveedorDTO> paginarProveedores(Pageable pageable) {
      Page<Proveedor> proveedores = proveedorRepo.findAll(pageable);
      return proveedores.map((proveedor)-> {
        ProveedorDTO proveedor_dto = modelMapper.map(proveedor, ProveedorDTO.class);
        if(!proveedor.getContacto().isEmpty()){
          List<ContactoDTO> lista_contacto = new ArrayList();
          for(Contacto c: proveedor.getContacto()){
            ContactoDTO contacto_dto = modelMapper.map(c, ContactoDTO.class);
            lista_contacto.add(contacto_dto);
          }
          proveedor_dto.setContacto_dto(lista_contacto);
        }
        return proveedor_dto;
      });
    }

  @Override
  public Optional<Proveedor> findByID(int id_proveedor) { 
    return proveedorRepo.findById(id_proveedor);
  }
}
