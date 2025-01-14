package mySystem.QuickSales.service;

import mySystem.QuickSales.iservice.IProveedorService;
import mySystem.QuickSales.iservice.IContactoService;
import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import mySystem.QuickSales.DTO.ContactoDTO;
import mySystem.QuickSales.DTO.ProveedorDTO;
import mySystem.QuickSales.model.Proveedor;
import mySystem.QuickSales.repository.ProveedorRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
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
      Proveedor p = modelMapper.map(proveedor_dto, Proveedor.class);
      proveedorRepo.save(p);
      
      if(!proveedor_dto.getContacto_dto().isEmpty() || proveedor_dto.getContacto_dto()!= null){
        for(ContactoDTO c : proveedor_dto.getContacto_dto()){
          c.setProveedor(p);
          contactoService.registrarContacto(c);
        }
      }
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
      System.err.println(e.getMessage());
    }
  }
  
  @Transactional
  @Override
  public void eliminarProveedor(ProveedorDTO proveedor_dto) {
    try {
      Optional<Proveedor> prov = proveedorRepo.findById(proveedor_dto.getId());
      if(prov.isPresent()){
        proveedorRepo.deleteById(proveedor_dto.getId());
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

  
  
}
