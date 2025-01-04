package mySystem.QuickSales.security;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import mySystem.QuickSales.model.User;
import mySystem.QuickSales.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserDetailServiceImpl implements UserDetailsService{
    
    @Autowired
    private UserRepository usuarioRepo;

    //Buscamos en la base de datos
    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
      Optional<User> usuarioOptional = usuarioRepo.findByUsername(username);
      
      if(usuarioOptional.isEmpty()){
          throw new UsernameNotFoundException(String.format("El usuario %s no existe", username));
      }
      
      User usuario = usuarioOptional.orElseThrow();
      
      List<GrantedAuthority> authorities = usuario.getRoles().stream()
              .map(role -> new SimpleGrantedAuthority(role.getName()))
              .collect(Collectors.toList());

      return new org.springframework.security.core.userdetails.User(usuario.getUsername(),
      usuario.getPassword(), usuario.isEnabled(),true,true,true,authorities);
    }
}
