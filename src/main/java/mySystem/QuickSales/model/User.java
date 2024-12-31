package mySystem.QuickSales.model;

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
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import java.util.Set;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id_user;
    
    @NotNull
    @NotBlank
    @Column(unique = true, nullable = false)
    private String username;
    
    @NotNull
    @NotBlank
    @Email
    private String email;
    
    @NotNull
    @NotBlank
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY) // Habilitamos solo la escritura en al momento de la creacion pero no se muestra en el json
    private String password;
    
    private boolean enabled;
    
    @PrePersist
    public void prePersist(){
        enabled = true;
    }
    
    @ManyToMany(fetch = FetchType.LAZY)
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
    private Set<Role> role;
}
