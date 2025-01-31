package mySystem.QuickSales.repository;

import java.util.List;
import mySystem.QuickSales.model.Pago;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
@EnableJpaRepositories
public interface PagoRepository extends JpaRepository<Pago, Integer>{
    
    @Query("SELECT p FROM Pago p WHERE p.comprobante.id = :id_comprobante")
    List<Pago> findByIdComprobante(@Param("id_comprobante") String id_comprobante);
  
    @Query("SELECT SUM(p.importe) FROM Pago p WHERE p.comprobante.id = :id_comprobante")
    Double sumatoriaPagosRealizados(@Param("id_comprobante") String id_comprobante);
}
