package mySystem.QuickSales.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "token")
public class Token {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Integer id;
  
  @Column(name = "access_token", nullable = false, unique = true)
  private String accessToken;
  
  @Column(name = "refresh_token", nullable = false, unique = true)
  private String refreshToken;
  
  @Column(name = "is_logged_out")
  private boolean loggedOut;
  
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "id_user", referencedColumnName = "id_user")
  private User user;
}
