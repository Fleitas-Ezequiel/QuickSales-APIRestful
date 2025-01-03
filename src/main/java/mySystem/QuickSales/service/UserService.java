package mySystem.QuickSales.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
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
        user.setPassword(password_encoder.encode(request.getPassword()));
        
        Role rol = roleService.createRoleIfNotExists("ROLE_"+request.getRole());
        user.setRole(Set.of(rol));
        user_repo.save(user);
    }

    @Override
    public void actualizarUsuario(User usuario) {
        
    }

    @Override
    public void eliminarUsuario(User usuario) {
        user_repo.deleteById(usuario.getId_user());
    }

    @Override
    public List<UserDTO> listarUsuarios() {
        List<UserDTO> lista_usuarios = new ArrayList();
        List<User> lista_entidad = user_repo.findAll();
        for(User usuario: lista_entidad){
            UserDTO user = model_mapper.map(usuario, UserDTO.class);
            lista_usuarios.add(user);
        }
        return lista_usuarios;
    }
    
    @Override
    public Optional<User> getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        final String username = authentication.getName();

        // Buscar el usuario en la base de datos o en un repositorio
        Optional<User> user = user_repo.findOneByEmail(username);
        
        return user;
    }

    @Override
    public void changePassword(String username, String newPassword) {
        User user = user_repo.findByUsername(username)
                     .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        user.setPassword(password_encoder.encode(newPassword));
        user_repo.save(user);
    }
}
