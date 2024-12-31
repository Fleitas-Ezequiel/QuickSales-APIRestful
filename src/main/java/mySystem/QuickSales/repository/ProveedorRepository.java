package mySystem.QuickSales.repository;

import java.util.List;
import mySystem.QuickSales.model.Proveedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository
@EnableJpaRepositories
public interface ProveedorRepository extends JpaRepository<Proveedor, Integer>{
  
  //Uso de JPQL
  @Query("SELECT p FROM Proveedor p WHERE p.id = ?1")
  List<Proveedor> findByFiltros(int id);
}
