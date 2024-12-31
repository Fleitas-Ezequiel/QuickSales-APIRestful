package mySystem.QuickSales.security;

import java.io.Serializable;
import lombok.Data;

@Data
public class AuthCredentials implements Serializable{

    //Esta clase recibe tanto el email como el password
    private String email;
    private String password;
}
