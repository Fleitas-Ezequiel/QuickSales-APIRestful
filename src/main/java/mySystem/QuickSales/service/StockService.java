package mySystem.QuickSales.service;

import mySystem.QuickSales.iservice.IStockService;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import mySystem.QuickSales.DTO.StockDTO;
import mySystem.QuickSales.DTO.StockDTOControl;
import mySystem.QuickSales.model.Stock;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import mySystem.QuickSales.repository.StockRepository;

@Service
public class StockService implements IStockService{

  @Autowired
  private StockRepository stock_repo;
  
  @Autowired
  private ComprobanteService comprobante_service;
  
  @Autowired
  private ProductoService producto_service;
  
  @Autowired
  private ModelMapper modelMapper;

  @Override
  public void registrarStock(StockDTO stock_dto) {
      try {
          Stock deposito = modelMapper.map(stock_dto, Stock.class);
          stock_repo.save(deposito);
          if(stock_dto.getComprobante_dto() != null){
              comprobante_service.registrarComprobante(stock_dto.getComprobante_dto());
          } else {   System.out.println("Se registro un stock previo a la implementacion del sistema");   }
          if(stock_dto.getProducto_dto() != null){
              producto_service.registrarProducto(stock_dto.getProducto_dto());
          } else {   System.out.println("Debe especificar el producto");   }
          
      } catch (Exception e) {
          System.err.println(e.getMessage());
      }
  }

  @Override
  public void actualizarStock(StockDTO stock_dto) {
    try {
      Optional<Stock> deposito = stock_repo.findById(stock_dto.getId_stock());
      if(deposito.isPresent()){
        Stock boleta_proveedor = modelMapper.map(deposito, Stock.class);
        stock_repo.save(boleta_proveedor);
      }
    } catch (Exception e) {
      System.err.println(e.getMessage());
    }
  }

  @Override
  public void eliminarStock(StockDTO stock_dto) {
      try {
          Optional<Stock> deposito = stock_repo.findById(stock_dto.getId_stock());
          if(deposito.isPresent()){
              stock_repo.deleteById(deposito.get().getId_stock());
          }
      } catch (Exception e) {
          System.err.println(e.getMessage());
      }
  }

  @Override
  public List<StockDTO> verStock() {
    List<StockDTO> lista = new ArrayList();
    for(Stock b : stock_repo.findAll()){
      lista.add(modelMapper.map(b, StockDTO.class));
    }
    return lista;
  }

    @Override
    public List<StockDTOControl> listarStocks() {
        List<StockDTOControl> lista = stock_repo.findStockControl();
        return lista;
    }
  
}
