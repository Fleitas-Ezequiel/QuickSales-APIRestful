package mySystem.QuickSales.iservice;

import java.util.List;
import mySystem.QuickSales.DTO.ContactoDTO;

public interface IContactoService {
  
  public void registrarContacto(ContactoDTO contacto);
  public void actualizarContacto(ContactoDTO contacto);
  public void eliminarContacto(ContactoDTO contacto);
  public List<ContactoDTO> verContactos();
  public List<ContactoDTO> listarPorProveedor(int id_proveedor);
}
