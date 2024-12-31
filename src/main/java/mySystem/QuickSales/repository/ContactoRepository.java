package mySystem.QuickSales.repository;

import java.util.List;
import mySystem.QuickSales.model.Contacto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository
@EnableJpaRepositories
public interface ContactoRepository extends JpaRepository<Contacto, Integer>{
  
  //Lenguaje JPQL de consultas
  @Query(value = "SELECT c FROM Contacto c WHERE c.proveedor.id = ?1")
  List<Contacto> findByProveedorId(int proveedorId);
}
