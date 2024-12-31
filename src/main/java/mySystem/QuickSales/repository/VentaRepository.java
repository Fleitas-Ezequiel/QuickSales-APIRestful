package mySystem.QuickSales.repository;

import mySystem.QuickSales.model.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository
@EnableJpaRepositories
public interface VentaRepository extends JpaRepository<Venta, Integer>{
  
}
