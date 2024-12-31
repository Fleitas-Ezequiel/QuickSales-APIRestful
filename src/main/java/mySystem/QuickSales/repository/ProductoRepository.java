package mySystem.QuickSales.repository;

import mySystem.QuickSales.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository
@EnableJpaRepositories
public interface ProductoRepository extends JpaRepository<Producto, Integer>{
  
}
