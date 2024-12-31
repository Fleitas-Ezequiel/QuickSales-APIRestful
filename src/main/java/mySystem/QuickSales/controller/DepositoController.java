package mySystem.QuickSales.controller;

import java.util.List;
import mySystem.QuickSales.DTO.StockDTO;
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
import mySystem.QuickSales.iservice.IStockService;

@RestController
@RequestMapping("/deposito")
public class DepositoController {
  
  @Autowired
  private IStockService depositoService;
  
  @PostMapping("/registry")
  @ResponseStatus(HttpStatus.CREATED)
  public ResponseEntity<String> registrarDeposito(@RequestBody List<StockDTO> depositos){
    try {
        for(StockDTO deposito: depositos){
          depositoService.registrarStock(deposito);
        }
        return ResponseEntity.ok("Deposito registrado correctamente");
    } catch (Exception e) {
      return ResponseEntity.badRequest().body("Error al registrar el producto");
    }
  }
  
  @PutMapping("/update")
  public ResponseEntity<String> actualizarDeposito(@RequestBody StockDTO deposito){
    try {
      depositoService.actualizarStock(deposito);
      return ResponseEntity.ok("Producto actualizado con exito");
    } catch (Exception e) {
      return ResponseEntity.badRequest().body("Error al actualizar el producto");
    }
  }
  
  @DeleteMapping("/delete")
  public ResponseEntity<String> eliminarDeposito(@RequestBody StockDTO deposito){
    try {
      depositoService.eliminarStock(deposito);
      return ResponseEntity.ok("Producto eliminado con exito");
    } catch (Exception e) {
      return ResponseEntity.badRequest().body("Error al eliminar el producto");
    }
  }
  
  @GetMapping("/list")
  @ResponseBody
  public List<StockDTO> listarDeposito(){
    return depositoService.verStock();
  }
}
