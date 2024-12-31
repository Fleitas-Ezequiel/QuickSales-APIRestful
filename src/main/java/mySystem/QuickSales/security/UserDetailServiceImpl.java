package mySystem.QuickSales.security;

import mySystem.QuickSales.model.User;
import mySystem.QuickSales.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailServiceImpl implements UserDetailsService{
    
    @Autowired
    private UserRepository usuarioRepo;

    //Buscamos en la base de datos
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
      User usuario = usuarioRepo
              .findOneByEmail(email)
              .orElseThrow(() -> new UsernameNotFoundException("El usuario con el email" +email+ "no existe"));

      return new UserDetailsImpl(usuario);
    }
}
