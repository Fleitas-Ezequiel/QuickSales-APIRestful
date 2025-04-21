package mySystem.QuickSales.repository;

import java.util.List;
import java.util.Optional;
import mySystem.QuickSales.model.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
@EnableJpaRepositories
public interface TokenRepository extends JpaRepository<Token, Integer>{
  
  @Query("""
         SELECT t FROM Token t INNER JOIN User u on t.user.idUser = u.idUser
         WHERE t.user.idUser = :userId and t.loggedOut = false
         """)
  List<Token> findAllAccessTokensByUser(Integer userId);
  
  Optional<Token> findByAccessToken(String token);
  
  @Query("""
         SELECT t FROM Token t WHERE t.refreshToken LIKE :refresh
         """)
  Optional<Token> findByRefreshToken(@Param("refresh") String refreshToken);
}
