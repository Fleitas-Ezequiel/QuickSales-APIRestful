package mySystem.QuickSales.iservice;

import java.util.List;
import java.util.Optional;
import mySystem.QuickSales.DTO.CreateUserRequest;
import mySystem.QuickSales.DTO.UserDTO;
import mySystem.QuickSales.model.User;

public interface IUserService{
    
    public void crearUsuario(CreateUserRequest request);
    public void actualizarUsuario(User usuario);
    public void eliminarUsuario(User usuario);
    public List<UserDTO> listarUsuarios();
    public Optional<User> getCurrentUser();
    public void changePassword(String username, String newPassword);
    public Optional<User> getUserByUsername(String username);
}
