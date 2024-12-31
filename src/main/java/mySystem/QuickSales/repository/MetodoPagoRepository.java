package mySystem.QuickSales.repository;

import mySystem.QuickSales.model.MetodoPago;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository
@EnableJpaRepositories
public interface MetodoPagoRepository extends JpaRepository<MetodoPago, Integer>{
  
}
