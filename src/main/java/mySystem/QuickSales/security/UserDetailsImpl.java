package mySystem.QuickSales.security;

import java.util.Collection;
import java.util.Collections;
import lombok.AllArgsConstructor;
import mySystem.QuickSales.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@AllArgsConstructor
public class UserDetailsImpl implements UserDetails{
    
    private final User usuario;
    
    @Override
    public String getPassword() {
      return usuario.getPassword();
    }

    public String getEmail() {
      return usuario.getEmail();
    }

    @Override
    public String getUsername(){
      return usuario.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
      return true;
    }

    @Override
    public boolean isAccountNonLocked() {
      return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
      return true;
    }

    @Override
    public boolean isEnabled() {
      return true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
      //este metodo es muy util en el caso de  que el usuario tuviera permisos o roles
      return Collections.emptyList(); 
    }
}
