package mySystem.QuickSales.iservice;

import java.util.List;
import java.util.Optional;
import mySystem.QuickSales.DTO.ContactoDTO;
import mySystem.QuickSales.model.Contacto;

public interface IContactoService {
  
  public void registrarContacto(ContactoDTO contacto);
  public void actualizarContacto(ContactoDTO contacto);
  public void eliminarContacto(int id_contacto);
  public List<ContactoDTO> verContactos();
  public List<ContactoDTO> listarPorProveedor(int id_proveedor);
  public Optional<Contacto> findById(int id_contacto);
}
