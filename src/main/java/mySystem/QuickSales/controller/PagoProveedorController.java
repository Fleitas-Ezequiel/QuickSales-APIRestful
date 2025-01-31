package mySystem.QuickSales.controller;


import java.util.List;
import mySystem.QuickSales.DTO.PagoDTO;
import mySystem.QuickSales.iservice.IPagoService;
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
@RequestMapping("/pagos")
public class PagoProveedorController {
  
  @Autowired
  private IPagoService pagoService;
  
  @PostMapping("/registrar")
  @ResponseStatus(HttpStatus.CREATED)
  public ResponseEntity<String> registrarPago(@RequestBody PagoDTO Pago){
    try {
      pagoService.registrarPagoProveedor(Pago);
      return ResponseEntity.ok("Registro exitoso");
    } catch (Exception e) {
      return ResponseEntity.badRequest().body("Error al registrar el Pago");
    }
  }
  
  @PatchMapping("/update")
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity<String> actualizarPago(@RequestBody PagoDTO Pago){
    try {
      pagoService.actualizrPagoProveedor(Pago);
      return ResponseEntity.ok("Pago actualizado correctamente");
    } catch (Exception e) {
      return ResponseEntity.badRequest().body("Error al actualizar el Pago");
    }
  }
  
  
  @DeleteMapping("/delete/{id}")
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity<String> eliminarPago(@PathVariable int id){
    try {
      pagoService.eliminarPagoProveedor(id);
      return ResponseEntity.ok("Pago eliminado con exito");
    } catch (Exception e) {
      return ResponseEntity.badRequest().body("Error al eliminar el Pago");
    }
  }
  
  @GetMapping("/list")
  @ResponseBody
  public List<PagoDTO> listarPagos(@RequestParam(required = false) String filtro){
    return pagoService.verPagoProveedor();
  }
  
  @GetMapping("/pagina")
  @ResponseBody
  public Page<PagoDTO> listarPagos(@PageableDefault(page = 0, size = 20) Pageable pageable){
      return pagoService.paginarPago(pageable);
  }
  
}
