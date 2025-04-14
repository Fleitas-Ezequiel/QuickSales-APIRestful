package mySystem.QuickSales.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import java.util.List;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user")
public class User {
    @Id
    @Column(name = "id_user")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idUser;
    
    @NotNull
    @NotBlank
    @Size(min = 6, max = 24)
    @Column(unique = true, nullable = false)
    private String username;
    
    @NotNull
    @NotBlank
    @Email
    private String email;
    
    @NotNull
    @NotBlank
//    @Size(min = 8, max = 64) // Establecemos un minimo y maximo para la longitud de la pass
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY) // Habilitamos solo la escritura en al momento de la creacion pero no se muestra en el json
    private String password;
    
    private boolean enabled;
    
    @PrePersist
    public void prePersist(){
        enabled = true;
    }
    
    @JsonIgnoreProperties({"users","handler","hibernateLazyInitializer"}) // Ignorando la lista de users de la entidad rol rompemos el bucle ciclico
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name="user_id"),
            inverseJoinColumns = @JoinColumn(name="role_id"),
            uniqueConstraints = {
                @UniqueConstraint(
                        columnNames = {"user_id","role_id"}
                )
            }
    )
    private List<Role> roles;
    
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<RefreshToken> tokens;
}
