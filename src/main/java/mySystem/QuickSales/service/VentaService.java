package mySystem.QuickSales.service;

import mySystem.QuickSales.iservice.IVentaService;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import mySystem.QuickSales.DTO.MetodoVentaDTO;
import mySystem.QuickSales.DTO.StockDTO;
import mySystem.QuickSales.DTO.VentaDTO;
import mySystem.QuickSales.model.Venta;
import mySystem.QuickSales.repository.VentaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VentaService implements IVentaService{

  @Autowired
  private VentaRepository venta_repo;
  
  @Autowired
  private StockService stock_service;
  
  @Autowired
  private MetodoVentaService metodo_venta_service;
  
  @Autowired
  private ModelMapper modelMapper;

  @Override
  public void registrarVenta(VentaDTO venta_dto){
      try {
          Venta venta = modelMapper.map(venta_dto, Venta.class);
          venta_repo.save(venta);
          if(venta_dto.getStock_dto() != null){
              for(StockDTO s: venta_dto.getStock_dto()){
                  stock_service.actualizarStock(s);
              }
          } else {
              System.out.println("Debe ingresar productos para vender");
          }
          if(venta_dto.getMetodo_venta_dto() != null){
              for(MetodoVentaDTO mv: venta_dto.getMetodo_venta_dto()){
                  metodo_venta_service.registrarMetodoVenta(mv);
              }
          } else {
              System.out.println("Debe ingresar el metodo de venta");
          }
      } catch (Exception e) {
          System.err.println(e.getMessage());
      }
  }

  @Override
  public void modificarVenta(VentaDTO venta_dto) {
    try {
      Optional<Venta> deposito = venta_repo.findById(venta_dto.getId());
      if(deposito.isPresent()){
        Venta boleta_proveedor = modelMapper.map(deposito, Venta.class);
        venta_repo.save(boleta_proveedor);
      }
    } catch (Exception e) {
      System.err.println(e.getMessage());
    }
  }

  @Override
  public void eliminarVenta(VentaDTO venta_dto){
      try {
          Optional<Venta> venta = venta_repo.findById(venta_dto.getId());
          if(venta.isPresent()){
              venta_repo.deleteById(venta.get().getId_venta());
          }
      } catch (Exception e) {
          System.err.println(e.getMessage());
      }
  }

  @Override
  public List<VentaDTO> verVenta() {
    List<VentaDTO> lista = new ArrayList();
    for(Venta b : venta_repo.findAll()){
      lista.add(modelMapper.map(b, VentaDTO.class));
    }
    return lista;
  }
  
  
  
}
