package mySystem.QuickSales.DTO;

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
    private List<Role> rol;
}
