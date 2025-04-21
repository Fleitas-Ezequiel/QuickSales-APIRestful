package mySystem.QuickSales.repository;

import java.util.Optional;
import mySystem.QuickSales.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
@EnableJpaRepositories
public interface UserRepository extends JpaRepository<User, Integer>{
    Optional<User> findOneByEmail(String email);
    Optional<User> findByUsername(String username);
    boolean existsByUsername(String usuario);
    
    @Query("""
           SELECT u FROM User u WHERE u.username = :user
           """)
    Optional<User> findOneByUsername(@Param("user") String username);
}
