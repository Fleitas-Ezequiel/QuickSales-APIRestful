package mySystem.QuickSales.service;

import mySystem.QuickSales.iservice.IProductoService;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import mySystem.QuickSales.DTO.ProductoDTO;
import mySystem.QuickSales.model.Producto;
import mySystem.QuickSales.repository.ProductoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductoService implements IProductoService{
  
  @Autowired
  private ProductoRepository producto_repo;

  @Autowired
  private ModelMapper modelMapper;
  
  @Override
  public void registrarProducto(ProductoDTO producto) {
    try {
      Producto p = modelMapper.map(producto, Producto.class);
      producto_repo.save(p);
    } catch (Exception e) {
      System.err.println(e.getMessage());
    }
  }

  @Override
  public void modificarProducto(ProductoDTO producto) {
    try {
      Optional<Producto> p = producto_repo.findById(producto.getId());
      if(p.isPresent()){
        Producto prod = p.get();
        prod = modelMapper.map(producto, Producto.class);
        producto_repo.save(prod);
      }
    } catch (Exception e) {
      System.err.println(e.getMessage());
    }
  }

  @Override
  public void eliminarProducto(ProductoDTO producto) {
    try {
      Optional<Producto> p = producto_repo.findById(producto.getId());
      if(p.isPresent()){
        producto_repo.deleteById(p.get().getId_producto());
      }
    } catch (Exception e) {
      System.err.println(e.getMessage());
    }
  }

  @Override
  public List<ProductoDTO> verProducto() {
    List<ProductoDTO> lista = new ArrayList();
    for(Producto p : producto_repo.findAll()){
      lista.add(modelMapper.map(p, ProductoDTO.class));
    }
    return lista;
  }
  
}
