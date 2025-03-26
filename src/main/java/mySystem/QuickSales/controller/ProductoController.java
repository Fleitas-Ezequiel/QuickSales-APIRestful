package mySystem.QuickSales.controller;

import java.util.List;
import mySystem.QuickSales.DTO.ProductoDTO;
import mySystem.QuickSales.iservice.IProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/productos")
public class ProductoController {
  
  @Autowired
  private IProductoService productoService;
  
  @DeleteMapping("/delete/{id}")
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity<String> eliminarProveedor(@PathVariable int id){
    try {
      productoService.eliminarProducto(id);
      return ResponseEntity.ok("Proveedor eliminado con exito");
    } catch (Exception e) {
      return ResponseEntity.badRequest().body("Error al eliminar el proveedor");
    }
  }
  
  @GetMapping("/list")
  @ResponseBody
  public List<ProductoDTO> listarProveedores(@RequestParam(required = false) String filtro){
    return productoService.verProducto();
  }
  
  @GetMapping("/pagina")
  @ResponseBody
  public Page listarProveedores(@PageableDefault(page = 0, size = 20) Pageable pageable){
      return productoService.paginarProductos(pageable);
  }
  
  @GetMapping("/single")
  @ResponseBody
  public ResponseEntity buscarProveedor(String nombre){
    try {
      return ResponseEntity.ok(productoService.verProveedorFiltrado(nombre));
    } catch (Exception e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }
    
  }
}
