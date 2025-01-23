package mySystem.QuickSales.controller;

import java.util.List;
import mySystem.QuickSales.DTO.ProveedorDTO;
import mySystem.QuickSales.iservice.IProveedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/proveedores")
public class ProveedorController {
  
  @Autowired
  private IProveedorService proveedorService;
  
  @PostMapping("/register")
  @ResponseStatus(HttpStatus.CREATED)
  public ResponseEntity<String> registrarProveedor(@RequestBody ProveedorDTO proveedor){
    try {
      proveedorService.registrarProveedor(proveedor);
      return ResponseEntity.ok("Registro exitoso");
    } catch (Exception e) {
      return ResponseEntity.badRequest().body("Error al registrar el proveedor");
    }
  }
  
  @PatchMapping("/update")
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity<String> actualizarProveedor(@RequestBody ProveedorDTO proveedor){
    try {
      proveedorService.modificarProveedor(proveedor);
      return ResponseEntity.ok("Proveedor actualizado correctamente");
    } catch (Exception e) {
      return ResponseEntity.badRequest().body("Error al actualizar el proveedor");
    }
  }
  
  
  @DeleteMapping("/delete/{id}")
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity<String> eliminarProveedor(@PathVariable int id){
    try {
      proveedorService.eliminarProveedor(id);
      return ResponseEntity.ok("Proveedor eliminado con exito");
    } catch (Exception e) {
      return ResponseEntity.badRequest().body("Error al eliminar el proveedor");
    }
  }
  
  @GetMapping("/list")
  @ResponseBody
  public List<ProveedorDTO> listarProveedores(@RequestParam(required = false) String filtro){
    return proveedorService.verProveedor();
  }
  
  @GetMapping("/pagina")
  @ResponseBody
  public Page listarProveedores(@PageableDefault(page = 0, size = 20) Pageable pageable){
      return proveedorService.paginarProveedores(pageable);
  }
}
