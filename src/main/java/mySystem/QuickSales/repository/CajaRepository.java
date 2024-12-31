package mySystem.QuickSales.repository;

import jakarta.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import mySystem.QuickSales.model.Caja;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;


@Repository
@EnableJpaRepositories
public interface CajaRepository extends JpaRepository<Caja, Integer>{
 
  @Transactional
  @Query("SELECT c FROM Caja c WHERE c.tipo = ?1")
  List<Caja> findByTipo(String tipo);
  
  @Transactional
  @Query("SELECT c FROM Caja c WHERE c.fecha_creacion = :fecha")
  List<Caja> findByFecha(LocalDate fecha);
  
  @Transactional
  @Query("SELECT c FROM Caja c WHERE c.fecha_creacion = :fecha AND c.tipo = :tipo")
  Optional<Caja> findByFechaTipo(LocalDate fecha, String tipo);
}
