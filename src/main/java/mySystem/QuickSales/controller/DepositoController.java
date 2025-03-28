package mySystem.QuickSales.controller;

import java.util.List;
import mySystem.QuickSales.DTO.StockDTO;
import mySystem.QuickSales.DTO.StockDTOControl;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.StreamingHttpOutputMessage;

@RestController
@RequestMapping("/deposito")
public class DepositoController {
  
  @Autowired
  private IStockService depositoService;
  
  @PostMapping("/register")
  @ResponseStatus(HttpStatus.CREATED)
  public ResponseEntity<String> registrarDeposito(@RequestBody StockDTO stock){
    try {
        depositoService.registrarStock(stock);
        return ResponseEntity.ok("Stock registrado correctamente");
    } catch (Exception e) {
      return ResponseEntity.badRequest().body("Error al registrar el stock \n "+e.getMessage());
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
  
  @GetMapping("/list-control")
  @ResponseBody
  public List<StockDTOControl> listarTotalStock(){
    return depositoService.listarStocks();
  }
  
  @GetMapping("/pagina")
  @ResponseBody
  public Page listarProveedores(@PageableDefault(page = 0, size = 20) Pageable pageable){
    try {
      return depositoService.paginarStock(pageable);
    } catch (Exception e) {
      System.err.println(e.getMessage());
      return null;
    }
  }
}
