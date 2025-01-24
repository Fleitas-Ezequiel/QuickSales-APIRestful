package mySystem.QuickSales.controller;


import mySystem.QuickSales.DTO.ComprobanteDTO;
import mySystem.QuickSales.iservice.IComprobanteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/comprobantes")
public class ComprobantesController {
  
  @Autowired
  public IComprobanteService comprobanteService;
  
  @PostMapping("/registrar")
  @ResponseStatus(HttpStatus.CREATED)
  public ResponseEntity<String> registrarComprobante(@RequestBody ComprobanteDTO comprobante_dto){
    try {
      comprobanteService.registrarComprobante(comprobante_dto);
      return ResponseEntity.ok("Comprobante registrado correctamente");
    } catch (Exception e) {
      return ResponseEntity.badRequest().body("Error de Registro de comprobante \n"+e.getMessage());
    }
  }
  
  @GetMapping("/pagina")
  @ResponseStatus(HttpStatus.OK)
  public Page registrarComprobante(@PageableDefault(page = 0, size = 10) Pageable pageable){
    return comprobanteService.paginarComprobantes(pageable);
  }
}
