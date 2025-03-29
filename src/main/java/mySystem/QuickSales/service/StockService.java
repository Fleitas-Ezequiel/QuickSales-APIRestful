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
import mySystem.QuickSales.repository.ProductoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import mySystem.QuickSales.repository.StockRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Service
public class StockService implements IStockService{

  @Autowired
  private StockRepository stock_repo;
  
  @Autowired
  private ComprobanteService comprobante_service;
  
  @Autowired
  private IProductoService producto_service;
  
  @Autowired
  private ProductoRepository producto_repository;
  
  @Autowired
  private ModelMapper modelMapper;

  @Override
  public void registrarStock(StockDTO stock_dto) {
    List<Stock> bulk = new ArrayList<>();
    
    Producto producto = new Producto();
    int id = stock_dto.getProducto_dto().getId_producto();
    Optional<Producto> producto_optional = producto_service.findProductoById(id);
    if(producto_optional.isPresent()){
      producto = producto_optional.get();
    } else {
      producto = modelMapper.map(stock_dto.getProducto_dto(), Producto.class);
      producto_repository.save(producto);
    }
    
    Comprobante comprobante = validateComprobante(stock_dto);
    
    for(int i=0; i<stock_dto.getCantidad(); i++){ //copiamos un mismo objeto para hacer la cantidad de mismos registros que necesitamos
      Stock deposito = new Stock();
      deposito.setIdStock(UUID.randomUUID().toString());
      deposito.setCodigo(stock_dto.getCodigo());
      deposito.setEstado("En almacen");
      deposito.setFecha_vencimiento(stock_dto.getFecha_vencimiento());
      deposito.setPrecio_compra(stock_dto.getPrecio_compra());
      deposito.setPrecio_venta(stock_dto.getPrecio_venta());
      deposito.setProducto(producto);
      deposito.setComprobante(comprobante);

      bulk.add(deposito);
    }
    
    try {
      stock_repo.saveAll(bulk);
    } catch (Exception e) {
      throw new CustomException(e.getMessage());
    }
  }
  
  private Comprobante validateComprobante(StockDTO stock_dto){
    if(!stock_dto.getComprobante_dto().getId().isBlank()){
      String id_comprobante = stock_dto.getComprobante_dto().getId();
      Optional<Comprobante> comprobanteOptional = comprobante_service.findComprobanteById(id_comprobante);
      if(comprobanteOptional.isPresent()){
        return comprobanteOptional.get();
      } else {
        throw new CustomException("Debe elegir un comprobante existente");
      }
    } else {
      System.out.println("Se registro un stock sin asociar a un comprobante de compra");
      return null;
    }
  }

  @Override
  public void actualizarStock(StockDTO stock_dto) {
    try {
      Optional<Stock> deposito = stock_repo.findById(stock_dto.getIdStock());
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
      Optional<Stock> deposito = stock_repo.findById(stock_dto.getIdStock());
      if(deposito.isPresent()){
        stock_repo.deleteById(deposito.get().getIdStock());
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
      List<Object[]> lista = stock_repo.findStockControl();
      List<StockDTOControl> stock_list = new ArrayList<>();
      for(Object[] stock: lista){
        StockDTOControl stock_object = new StockDTOControl();
        stock_object.setIdProducto((Integer)stock[0]);
        stock_object.setProducto((String)stock[1]);
        stock_object.setMarca((String)stock[2]);
        stock_object.setMedida((String)stock[3]);
        stock_object.setTipo((String)stock[4]);
        stock_object.setDescripcion((String)stock[5]);
        stock_object.setCantidad((Long)stock[6]);
        
        stock_list.add(stock_object);
      }
      return stock_list;
    }

  @Override
  public Page paginarStock(Pageable pageable) {
    Page<Stock> stocks = stock_repo.findAll(pageable);
    return stocks.map((stock) -> {
      return modelMapper.map(stock, StockDTO.class);
    });
  }

  @Override
  public Page paginarStockPorProducto(Pageable pageable, int id_producto, String estado) {
    Page<Stock> stocks = stock_repo.findByProductoIdProductoAndEstado(id_producto, estado, pageable);
    return stocks.map(stock -> modelMapper.map(stock, StockDTO.class));
  }
  
}
