package mySystem.QuickSales.repository;

import jakarta.transaction.Transactional;
import java.util.List;
import mySystem.QuickSales.model.Stock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
@EnableJpaRepositories
public interface StockRepository extends JpaRepository<Stock, String>{
  @Transactional
  @Query("SELECT p.idProducto, p.producto, p.marca, p.medida, p.tipo, p.descripcion, COUNT(s.idStock) "
          + "FROM Producto p "
          + "JOIN p.stock s "
          + "WHERE s.estado = 'En almacen' "
          + "GROUP BY p.idProducto")
  List<Object[]> findStockControl();
  
  @Transactional
  @Query("SELECT p.idProducto, p.producto, p.marca, p.medida, p.tipo, p.descripcion, COUNT(s.idStock) "
          + "FROM Producto p "
          + "JOIN p.stock s "
          + "WHERE s.estado = 'En almacen' "
          + "AND (:product IS NULL OR :product = '' OR p.producto LIKE LOWER(CONCAT('%', :product, '%')))"
          + "GROUP BY p.idProducto")
  List<Object[]> findStockControlByProduct(@Param("product") String producto);
  
  Page<Stock> findByProductoIdProductoAndEstado(int idProducto, String estado,Pageable pageable);
}
