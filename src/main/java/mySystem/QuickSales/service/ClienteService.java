package mySystem.QuickSales.service;

import mySystem.QuickSales.iservice.IClienteService;
import java.util.ArrayList;
import java.util.List;
import mySystem.QuickSales.DTO.ClienteDTO;
import mySystem.QuickSales.model.Cliente;
import mySystem.QuickSales.repository.ClienteRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService implements IClienteService{
  
  @Autowired
  private ModelMapper modelMapper;
  
  @Autowired
  public ClienteRepository clienteRepo;

  @Override
  public void registrarCliente(ClienteDTO cliente) {
    Cliente client = new Cliente();
    client.setId_cliente(cliente.getId());
    client.setNombre(cliente.getNombre());
    client.setApellido(cliente.getApellido());
    client.setTelefono(cliente.getTelefono());
    clienteRepo.save(client);
  }

  @Override
  public void actualizarCliente(ClienteDTO cliente) {
    Cliente client = clienteRepo.findById(cliente.getId()).orElse(null);
    if(client != null){
      client.setId_cliente(cliente.getId());
      client.setNombre(cliente.getNombre());
      client.setApellido(cliente.getApellido());
      client.setTelefono(cliente.getTelefono());
      clienteRepo.save(client);
    }
  }

  @Override
  public void eliminarCliente(ClienteDTO cliente) {
    clienteRepo.deleteById(cliente.getId());
  }

  @Override
  public List<ClienteDTO> mostrarClientes() {
    List<ClienteDTO> clientes = new ArrayList();
    try {
      for(Cliente cli : clienteRepo.findAll()){
        ClienteDTO c = modelMapper.map(cli, ClienteDTO.class);
        clientes.add(c);
      }
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
    return clientes;
  }
}
