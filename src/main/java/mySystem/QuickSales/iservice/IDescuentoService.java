package mySystem.QuickSales.iservice;

import java.util.List;
import mySystem.QuickSales.DTO.DescuentoDTO;
import mySystem.QuickSales.model.Descuento;

public interface IDescuentoService {
    public void registrarDescuento(DescuentoDTO descuento);
    public void modificarDescuento(DescuentoDTO descuento);
    public void eliminarDescuento(DescuentoDTO descuento);
    public Descuento buscarDescuento();
    public List<DescuentoDTO> listarDescuento();
}
