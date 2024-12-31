package mySystem.QuickSales.controller;

import java.util.List;
import mySystem.QuickSales.DTO.ClienteDTO;
import mySystem.QuickSales.iservice.IClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/client")
public class ClienteController {
  
  @Autowired
  private IClienteService clienteService;
  
  @PostMapping("/registry")
  @ResponseStatus(HttpStatus.CREATED)
  public ResponseEntity<String> registrarCliente(@RequestBody ClienteDTO cliente){
    try {
        clienteService.registrarCliente(cliente);
        return ResponseEntity.ok("Cliente registrado con exito");
    } catch (Exception e) {
        return ResponseEntity.badRequest().body("Error al registrar el cliente");
    }
  }
  
  @PutMapping("/update")
  public ResponseEntity<String> actualizarCliente(@RequestBody ClienteDTO cliente){
    try {
        clienteService.actualizarCliente(cliente);
        return ResponseEntity.ok("Cliente actualizado correctamente");
    } catch (Exception e) {
        return ResponseEntity.badRequest().body("Error al actualziar el cliente");
    }
  }
  
  @DeleteMapping("/delete")
  public ResponseEntity<String> borrarCliente(@RequestBody ClienteDTO cliente){
    try {
      clienteService.eliminarCliente(cliente);
      return ResponseEntity.ok("Cliente eliminado correctamente");
    } catch (Exception e) {
      return ResponseEntity.badRequest().body("Error al eliminar el cliente");
    }
  }
  
  @GetMapping("/list")
  @ResponseBody
  public List<ClienteDTO> listarClientes(){
    try {
      return clienteService.mostrarClientes();
    } catch (Exception e) {
      return (List<ClienteDTO>) ResponseEntity.badRequest().body("Error al listar los clientes");
    }
  }
}
