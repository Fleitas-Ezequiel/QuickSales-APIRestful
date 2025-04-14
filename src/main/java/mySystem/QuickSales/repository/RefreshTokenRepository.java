package mySystem.QuickSales.repository;

import java.util.Optional;
import mySystem.QuickSales.model.RefreshToken;
import mySystem.QuickSales.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long>{
  Optional<RefreshToken> findByToken(String token);
  void deleteByUser(User user);
}
