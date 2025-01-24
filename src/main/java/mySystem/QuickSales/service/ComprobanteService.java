package mySystem.QuickSales.service;

import mySystem.QuickSales.iservice.IComprobanteService;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import mySystem.QuickSales.DTO.ComprobanteDTO;
import mySystem.QuickSales.DTO.ProveedorDTO;
import mySystem.QuickSales.iservice.IProveedorService;
import mySystem.QuickSales.model.Comprobante;
import mySystem.QuickSales.model.Proveedor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import mySystem.QuickSales.repository.ComprobanteRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Service
public class ComprobanteService implements IComprobanteService{
  
  @Autowired
  private ComprobanteRepository boleta_repo;
  
  @Autowired
  private IProveedorService proveedorService;

  @Autowired
  private ModelMapper modelMapper;
  
  @Override
  public void registrarComprobante(ComprobanteDTO comprobante_dto) {
    try {
      Comprobante comprobante = modelMapper.map(comprobante_dto, Comprobante.class);
      Optional<Proveedor> proveedor = proveedorService.findByID(comprobante_dto.getProveedor_dto().getId());
      if(proveedor.isPresent()){
        comprobante.setProveedor(proveedor.get());
        boleta_repo.save(comprobante);
      } else {
        System.out.println("No se encontro el proveedor del comprobante");
      }
    } catch (Exception e) {
      System.err.println("Error en el registro del comprobante \n"+e.getMessage());
    }
  }

  @Override
  public void actualizarComprobante(ComprobanteDTO comprobante_dto) {
    try {
      Optional<Comprobante> comprobante_optional = boleta_repo.findById(comprobante_dto.getId());
      if(comprobante_optional.isPresent()){
        Comprobante comprobante = modelMapper.map(comprobante_dto, Comprobante.class);
        Optional<Proveedor> proveedor = proveedorService.findByID(comprobante_dto.getProveedor_dto().getId());
        if(proveedor.isPresent()){
          comprobante.setProveedor(proveedor.get());
          boleta_repo.save(comprobante);
        } else {
          System.out.println("No se encontro el proveedor del comprobante");
        }
      }
    } catch (Exception e) {
      System.err.println("Error en la actualizacion del comprobante \n"+e.getMessage());
    }
  }

  @Override
  public void eliminarComprobante(String id_comprobante) {
    try {
      Optional<Comprobante> comprobante = boleta_repo.findById(id_comprobante);
      if(comprobante.isPresent()){
        boleta_repo.deleteById(comprobante.get().getId());
      }
    } catch (Exception e) {
      System.err.println("Error en la eliminacion del comprobante \n"+e.getMessage());
    }
  }

  @Override
  public List<ComprobanteDTO> verComprobantes() {
    List<ComprobanteDTO> lista = new ArrayList();
    for(Comprobante c : boleta_repo.findAll()){
      ProveedorDTO proveedor_dto = modelMapper.map(c.getProveedor(), ProveedorDTO.class);
      ComprobanteDTO comprobante_dto = modelMapper.map(c, ComprobanteDTO.class);
      comprobante_dto.setProveedor_dto(proveedor_dto);
      lista.add(comprobante_dto);
    }
    return lista;
  }

  @Override
  public Page<ComprobanteDTO> paginarComprobantes(Pageable pageable) {
    Page<Comprobante> comprobantes = boleta_repo.findAll(pageable);
    return comprobantes.map((comprobante)-> {
      ProveedorDTO proveedor_dto = modelMapper.map(comprobante.getProveedor(), ProveedorDTO.class);
      ComprobanteDTO comprobante_dto = modelMapper.map(comprobante, ComprobanteDTO.class);
      comprobante_dto.setProveedor_dto(proveedor_dto);
      return comprobante_dto;
    });
  }

  @Override
  public Optional<Comprobante> findComprobanteById(String id_comprobante) {
   Optional<Comprobante> comprobante_optional = boleta_repo.findById(id_comprobante);
   return comprobante_optional;
  }
}
