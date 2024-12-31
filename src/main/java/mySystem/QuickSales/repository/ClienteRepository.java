package mySystem.QuickSales.repository;

import mySystem.QuickSales.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository
@EnableJpaRepositories
public interface ClienteRepository extends JpaRepository<Cliente, Integer>{
  
}
