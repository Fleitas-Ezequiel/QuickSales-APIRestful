package mySystem.QuickSales.security;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthCredentials implements Serializable{

    //Esta clase recibe tanto el username como el password
    private String username;
    private String email;
    private String password;
}
