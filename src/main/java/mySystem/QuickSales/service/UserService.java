package mySystem.QuickSales.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import mySystem.QuickSales.DTO.CreateUserRequest;
import mySystem.QuickSales.DTO.UserDTO;
import mySystem.QuickSales.iservice.IUserService;
import mySystem.QuickSales.model.Role;
import mySystem.QuickSales.model.User;
import mySystem.QuickSales.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService{
    
    @Autowired
    private UserRepository user_repo;
    
    @Autowired
    private RoleService roleService;

    @Autowired
    private PasswordEncoder password_encoder;
    
    @Autowired
    private ModelMapper model_mapper;

    @Override
    public void crearUsuario(CreateUserRequest request) {
        // Crear el usuario
        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(password_encoder.encode(request.getPassword()));
        
        List<Role> rol = new ArrayList<>();
        rol.add(roleService.createRoleIfNotExists("ROLE_"+request.getRole()));
        user.setRoles((List<Role>) rol);
        user_repo.save(user);
    }

    @Override
    public void actualizarUsuario(User usuario) {
        Optional<User> usuario_optional = getCurrentUser();
        
        if(usuario_optional.isPresent()){
            User user_data = usuario_optional.get();
            if(user_data.getId_user() == usuario.getId_user()){
                user_repo.save(usuario);
            }   else {
                System.out.println("El id no coincide con el usuario autenticado");
            }
        }   else {
            System.out.println("Usuario no encontrado");
        }
    }

    @Override
    public void eliminarUsuario(User usuario) {
        Optional<User> usuario_optional = getCurrentUser();
        
        if(usuario_optional.isPresent()){
            User user_data = usuario_optional.get();
            if(user_data.getId_user() == usuario.getId_user()){
                user_repo.deleteById(usuario.getId_user());
            }   else {
                System.out.println("El id no coincide con el usuario autenticado");
            }
        }   else {
            System.out.println("Usuario no encontrado");
        }
    }

    @Override
    public List<UserDTO> listarUsuarios() {
        List<UserDTO> lista_usuarios = new ArrayList();
        List<User> lista_entidad = user_repo.findAll();
        for(User usuario: lista_entidad){
            UserDTO user = model_mapper.map(usuario, UserDTO.class);
            user.setRol(roleService.getRoleByUsername(user.getUsername()));
            lista_usuarios.add(user);
        }
        return lista_usuarios;
    }
    
    @Override
    public Optional<User> getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        final String username = authentication.getName();
        // Buscar el usuario en la base de datos o en un repositorio
        Optional<User> user = user_repo.findByUsername(username);
        
        return user;
    }

    @Override
    public void changePassword(String username, String newPassword) {
        User user = user_repo.findByUsername(username)
                     .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        user.setPassword(password_encoder.encode(newPassword));
        user_repo.save(user);
    }

    @Override
    public User getUserByUsername(String username) {
        User usuario = new User();
        Optional<User> user_optional = user_repo.findByUsername(username);
        if(user_optional.isPresent()){
          usuario = user_optional.get();
        }
        return usuario;
    }
}
