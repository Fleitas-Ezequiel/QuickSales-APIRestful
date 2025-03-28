package mySystem.QuickSales.repository;

import jakarta.transaction.Transactional;
import java.util.List;
import mySystem.QuickSales.model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository
@EnableJpaRepositories
public interface StockRepository extends JpaRepository<Stock, String>{
  @Transactional
  @Query("SELECT p.producto, p.marca, p.medida, p.descripcion, COUNT(s.id_stock) "
          + "FROM Producto p "
          + "JOIN p.stock s "
          + "WHERE s.estado = 'En almacen' "
          + "GROUP BY p.id_producto")
  List<Object[]> findStockControl();
}
