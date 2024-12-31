package mySystem.QuickSales.controller;

import java.util.List;
import mySystem.QuickSales.DTO.VentaDTO;
import mySystem.QuickSales.iservice.IVentaService;
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
@RequestMapping("/ventas")
public class VentaController {
  
  @Autowired
  private IVentaService ventaService;
  
  @PostMapping("/registry")
  @ResponseStatus(HttpStatus.CREATED)
  public ResponseEntity<String> registrarVenta(@RequestBody VentaDTO venta){
    try {
      ventaService.registrarVenta(venta);
      return ResponseEntity.ok("Venta exitosa");
    } catch (Exception e) {
      return ResponseEntity.badRequest().body("Error en registrar la venta");
    }
  }
  
  @PutMapping("/update")
  public ResponseEntity<String> modificarVenta(@RequestBody VentaDTO venta){
    try {
      ventaService.modificarVenta(venta);
      return ResponseEntity.ok("Modificacion exitosa");
    } catch (Exception e) {
      return ResponseEntity.badRequest().body("Error en la modificacion de la venta");
    }
  }
  
  @DeleteMapping("/delete")
  public ResponseEntity<String> eliminarVenta(@RequestBody VentaDTO venta){
    try {
      ventaService.eliminarVenta(venta);
      return ResponseEntity.ok("Venta eliminada con exito");
    } catch (Exception e) {
      return ResponseEntity.badRequest().body("Error al eliminar la venta");
    }
  }
  
  @GetMapping("/list")
  @ResponseBody
  public List<VentaDTO> verVentas(){
    return ventaService.verVenta();
  }
}
