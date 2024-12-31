package mySystem.QuickSales.iservice;

import java.util.List;
import mySystem.QuickSales.DTO.ClienteDTO;

public interface IClienteService {
  
  public void registrarCliente(ClienteDTO cliente);
  public void actualizarCliente(ClienteDTO cliente);
  public void eliminarCliente(ClienteDTO cliente);
  public List<ClienteDTO> mostrarClientes();
}
