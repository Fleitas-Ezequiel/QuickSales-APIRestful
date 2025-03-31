package mySystem.QuickSales.service;

import mySystem.QuickSales.iservice.IProductoService;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import mySystem.QuickSales.DTO.ProductoDTO;
import mySystem.QuickSales.configuration.CustomException;
import mySystem.QuickSales.model.Producto;
import mySystem.QuickSales.repository.ProductoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
      Optional<Producto> p = producto_repo.findById(producto.getIdProducto());
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
  public void eliminarProducto(int id_producto) {
    try {
      Optional<Producto> p = producto_repo.findById(id_producto);
      if(p.isPresent()){
        producto_repo.deleteById(p.get().getIdProducto());
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

  @Override
  public Optional<Producto> findProductoById(int id) {
    return producto_repo.findById(id);
  }

  @Override
  public Page<ProductoDTO> paginarProductos(Pageable pageable) {
    Page<Producto> productos = producto_repo.findAll(pageable);
    return productos.map((producto) -> {
      return modelMapper.map(producto, ProductoDTO.class);
    });
  }

  @Override
  public ProductoDTO verProveedorFiltrado(String producto) {
    Optional<Producto> producto_optional = producto_repo.findByFiltro(producto);
    if (producto_optional.isPresent()) {
      return modelMapper.map(producto_optional, ProductoDTO.class);
    } else {
      throw new CustomException("No se encontro el producto");
    }
  }
}
