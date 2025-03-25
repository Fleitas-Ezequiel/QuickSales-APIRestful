package mySystem.QuickSales.service;

import mySystem.QuickSales.iservice.IStockService;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import mySystem.QuickSales.DTO.StockDTO;
import mySystem.QuickSales.DTO.StockDTOControl;
import mySystem.QuickSales.configuration.CustomException;
import mySystem.QuickSales.iservice.IProductoService;
import mySystem.QuickSales.model.Comprobante;
import mySystem.QuickSales.model.Producto;
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
  private IProductoService producto_service;
  
  @Autowired
  private ModelMapper modelMapper;

  @Override
  public void registrarStock(StockDTO stock_dto) {
    List<Stock> bulk = new ArrayList<>();

    Stock deposito = new Stock();
    deposito.setCodigo(stock_dto.getCodigo());
    deposito.setEstado("En almacen");
    deposito.setFecha_vencimiento(stock_dto.getFecha_vencimiento());
    deposito.setPrecio_compra(stock_dto.getPrecio_compra());
    deposito.setPrecio_venta(stock_dto.getPrecio_venta());

    if(!stock_dto.getComprobante_dto().getId().isBlank()){
      String id_comprobante = stock_dto.getComprobante_dto().getId();
      Optional<Comprobante> comprobanteOptional = comprobante_service.findComprobanteById(id_comprobante);
      if(comprobanteOptional.isPresent()){
        deposito.setComprobante(comprobanteOptional.get());
      } else {
        throw new CustomException("Debe elegir un comprobante existente");
      }
    } else {
      System.out.println("Se registro un stock sin asociar a un comprobante de compra");
    }
//    if(stock_dto.getProducto_dto() != null && stock_dto.getProducto_dto().getId() != 0){
      int id = stock_dto.getProducto_dto().getId();
      Optional<Producto> producto = producto_service.findProductoById(id);
      if(producto.isPresent()){
        deposito.setProducto(modelMapper.map(stock_dto.getProducto_dto(), Producto.class));
      } else {
        producto_service.registrarProducto(stock_dto.getProducto_dto());
      }
//    } else {
//      throw new CustomException("Debe especificar el producto");
//    }
    
    for(int i=0; i<stock_dto.getCantidad(); i++){ //copiamos un mismo objeto para hacer la cantidad de mismos registros que necesitamos
      deposito.setId_stock(UUID.randomUUID().toString());
      bulk.add(deposito);
    }
    
    System.out.println(bulk);
    stock_repo.saveAll(bulk);
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
