package mySystem.QuickSales.service;

import mySystem.QuickSales.iservice.IPagoService;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import mySystem.QuickSales.DTO.PagoDTO;
import mySystem.QuickSales.configuration.CustomException;
import mySystem.QuickSales.iservice.IComprobanteService;
import mySystem.QuickSales.model.Comprobante;
import mySystem.QuickSales.model.Pago;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import mySystem.QuickSales.repository.PagoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Service
public class PagoService implements IPagoService{

  @Autowired
  private PagoRepository pago_proveedor_repo;
  
  @Autowired
  private IComprobanteService comprobante_service;
  
  @Autowired
  private ModelMapper modelMapper;

  @Override
  public void registrarPagoProveedor(PagoDTO pago_dto) {
    Pago pago = modelMapper.map(pago_dto, Pago.class);
    Optional<Comprobante> comprobante = comprobante_service.findComprobanteById(pago_dto.getComprobante().getId());
    if(comprobante.isPresent()){
      pago.setFecha(new Date());
      if(pagoPrevio(comprobante.get().getId()) > 0){
        Double pago_actual = pago_dto.getImporte() + pagoPrevio(comprobante.get().getId());
        if(pago_actual <= comprobante.get().getImporte()){
          pago_proveedor_repo.save(pago);
        } else {
            throw new CustomException("La suma de los pagos excede el importe total");
        }
      } else {
        if(pago_dto.getImporte() <= comprobante.get().getImporte()){
          pago_proveedor_repo.save(pago);
        } else {
            System.err.println("Pago excedido");
            throw new CustomException("El pago es mayor al importe adeudado");
        }
      }
    }
  }
  
  private Double pagoPrevio(String id_comprobante){
    Double pagos = pago_proveedor_repo.sumatoriaPagosRealizados(id_comprobante);
    if(pagos != null){
      return pagos;
    }
    return 0d;
  }

  @Override
  public void actualizrPagoProveedor(PagoDTO pago_dto) {
    try {
      Optional<Pago> pago = pago_proveedor_repo.findById(pago_dto.getId());
      if(pago.isPresent()){
        Pago pago_prov = modelMapper.map(pago, Pago.class);
        pago_proveedor_repo.save(pago_prov);
      }
    } catch (Exception e) {
      System.err.println(e.getMessage());
    }
  }

  @Override
  public void eliminarPagoProveedor(int id_pago) {
    try {
      Optional<Pago> pago = pago_proveedor_repo.findById(id_pago);
      if(pago.isPresent()){
        pago_proveedor_repo.deleteById(pago.get().getId_pago());
      }
    } catch (Exception e) {
      System.err.println(e.getMessage());
    }
  }

  @Override
  public List<PagoDTO> verPagoProveedor() {
    List<PagoDTO> lista = new ArrayList();
    for(Pago pago : pago_proveedor_repo.findAll()){
      lista.add(modelMapper.map(pago, PagoDTO.class));
    }
    return lista;
  }

  @Override
  public Page<PagoDTO> paginarPago(Pageable pageable) {
    Page<Pago> pagos = pago_proveedor_repo.findAll(pageable);
    return pagos.map( (pago)-> {
      PagoDTO pago_dto = modelMapper.map(pago, PagoDTO.class);
      return pago_dto;
    });
  }

    @Override
    public List<PagoDTO> findByComprobanteId(String id_comprobante) {
      List<Pago> pago_list = pago_proveedor_repo.findByIdComprobante(id_comprobante);
      if(!pago_list.isEmpty()){
        List<PagoDTO> pago_dto_list = new ArrayList<>();
        for(Pago pago: pago_list ){
          PagoDTO pago_dto = modelMapper.map(pago, PagoDTO.class);
          pago_dto_list.add(pago_dto);
        }
        return pago_dto_list;
      }
      return null;
    }
}
