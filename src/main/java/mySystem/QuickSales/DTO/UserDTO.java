package mySystem.QuickSales.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mySystem.QuickSales.model.Role;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO implements Serializable{
    private String username;
    private String email;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    private List<Role> rol;
}
