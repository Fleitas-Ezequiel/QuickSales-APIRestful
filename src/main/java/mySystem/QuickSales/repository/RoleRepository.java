package mySystem.QuickSales.repository;

import java.util.List;
import java.util.Optional;
import mySystem.QuickSales.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
@EnableJpaRepositories
public interface RoleRepository extends JpaRepository<Role, Integer>{
    Optional<Role> findByName(String username);
    
    @Query("SELECT r FROM Role r JOIN r.users u WHERE u.username = :user")
    List<Role> findByUsername(@Param("user") String username);
}
