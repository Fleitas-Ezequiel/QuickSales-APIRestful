package mySystem.QuickSales.repository;

import java.util.Optional;
import mySystem.QuickSales.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository
@EnableJpaRepositories
public interface ProductoRepository extends JpaRepository<Producto, Integer>{
  
  @Query("SELECT p FROM Producto p WHERE p.producto = ?1")
  Optional<Producto> findByFiltro(String producto);
}
