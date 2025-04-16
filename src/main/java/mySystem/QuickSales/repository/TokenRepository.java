package mySystem.QuickSales.repository;

import java.util.List;
import java.util.Optional;
import mySystem.QuickSales.model.Token;
import mySystem.QuickSales.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenRepository extends JpaRepository<Token, Integer>{
  
  @Query("""
         SELECT t FROM Token t INNER JOIN User u on t.user.id = u.id
         WHERE t.user.id = :userId and t.loggedOut = false
         """)
  List<Token> findAllAccessTokensByUser(Integer userId);
  
  Optional<Token> findByAccessToken(String token);
  
  Optional<Token> findByRefreshToken(String token);
}
