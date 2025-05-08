package mySystem.QuickSales.service;

import mySystem.QuickSales.iservice.IVentaService;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import mySystem.QuickSales.DTO.MetodoVentaDTO;
import mySystem.QuickSales.DTO.StockDTO;
import mySystem.QuickSales.DTO.VentaDTO;
import mySystem.QuickSales.configuration.CustomException;
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
        Venta venta = new Venta();
        venta.setIdVenta(null);
        venta.setFecha(venta_dto.getFecha_venta());
        venta.setImporte(venta_dto.getImporte());
        venta.setDetalle(venta_dto.getDetalle());
        venta_repo.save(venta);
        
        // verificamos si envio una lista de venta vacia
        if(venta_dto.getStock_dto() != null){
          for(StockDTO s: venta_dto.getStock_dto()){
            s.setVenta_dto(modelMapper.map(venta, VentaDTO.class));
            stock_service.actualizarStock(s);
          }
        } else {
          throw new CustomException("Debe ingresar productos para vender");
        }
        //verificamos si envio un metodo de pago invalido
        if(venta_dto.getMetodo_venta_dto() != null){
            for(MetodoVentaDTO mv: venta_dto.getMetodo_venta_dto()){
              mv.setVenta_dto(modelMapper.map(venta, VentaDTO.class));
              metodo_venta_service.registrarMetodoVenta(mv);
            }
        } else {
           throw new CustomException("Debe ingresar el metodo de venta");
        }
      } catch (Exception e) {
        System.err.println("Error en el registro de venta");
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
              venta_repo.deleteById(venta.get().getIdVenta());
          }
      } catch (Exception e) {
          System.err.println(e.getMessage());
      }
  }

  @Override
  public List<VentaDTO> verVenta() {
    List<VentaDTO> lista = new ArrayList();
    for(Venta v : venta_repo.findAll()){
      VentaDTO venta_dto = new VentaDTO();
      venta_dto.setId(v.getIdVenta());
      venta_dto.setFecha_venta(v.getFecha());
      venta_dto.setImporte(v.getImporte());
      venta_dto.setDetalle(v.getDetalle());
      lista.add(venta_dto);
    }
    return lista;
  }
  
  
  
}
