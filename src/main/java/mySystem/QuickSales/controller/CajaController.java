package mySystem.QuickSales.controller;

import java.util.List;
import mySystem.QuickSales.DTO.CajaDTO;
import mySystem.QuickSales.iservice.ICajaService;
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
@RequestMapping("/caja")
public class CajaController {
  
  @Autowired
  private ICajaService cajaService;
  
  @PostMapping("/registry")
  @ResponseStatus(HttpStatus.CREATED)
  public ResponseEntity<String> registrarCaja(@RequestBody CajaDTO caja){
    try {
      cajaService.registrarCaja(caja);
      return ResponseEntity.ok("Caja registrada con exito");
    } catch (Exception e) {
      return ResponseEntity.badRequest().body("Error al registrar la caja");
    }
  }
  
  @PutMapping("/update")
  public ResponseEntity<String> actualizarCaja(@RequestBody CajaDTO caja){
    try {
      cajaService.modificarCaja(caja);
      return ResponseEntity.ok("Caja actualizada con exito");
    } catch (Exception e) {
      return ResponseEntity.badRequest().body("Error al actualizar la caja");
    }
  }
  
  
  @DeleteMapping("/delete")
  public ResponseEntity<String> eliminarCaja(@RequestBody CajaDTO caja){
    try {
      cajaService.eliminarCaja(caja);
      return ResponseEntity.ok("Caja eliminada con exito");
    } catch (Exception e) {
      return ResponseEntity.badRequest().body("Error al eliminar la caja");
    }
  }
  
  
  @GetMapping("/list")
  @ResponseBody
  public List<CajaDTO> listarCaja(){
    return cajaService.verCajas();
  }
  
}
