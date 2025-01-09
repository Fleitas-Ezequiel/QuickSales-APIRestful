package mySystem.QuickSales.controller;

import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import mySystem.QuickSales.DTO.ChangePasswordRequest;
import mySystem.QuickSales.DTO.UserDTO;
import mySystem.QuickSales.iservice.IUserService;
import mySystem.QuickSales.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/user")
public class UsuarioController {

    @Autowired
    private final IUserService userService;

    @PostMapping("/change-password")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> changePassword(@RequestBody ChangePasswordRequest request) {
        try {
            userService.changePassword(request.getUsername(), request.getNew_password());
            return ResponseEntity.ok("Contraseña cambiada correctamente.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error en el cambio de password");
        }
    }
    
    @GetMapping("/list")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public List<UserDTO> listarUsuario(){
        try {
            return userService.listarUsuarios();
        } catch (Exception e) {
            return (List<UserDTO>) ResponseEntity.badRequest().body("Error al solicitar los datos \n"+e);
        }
    }
    
    @PatchMapping("/edit")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> modificarUsuario(@RequestBody UserDTO user_dto){
        try {
            User user_entity = new User();
            user_entity.setUsername(user_dto.getUsername());
            user_entity.setEmail(user_dto.getEmail());
            user_entity.setRoles(user_dto.getRol());
            userService.actualizarUsuario(user_entity);
            return ResponseEntity.ok("Usuario actualizado correctamente");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error en la actualizacion del usuario");
        }
    }
    
    @GetMapping("/user-details")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public UserDTO buscarUsuario(){
        Optional<User> usuario_optional = userService.getCurrentUser();
        UserDTO user_dto = new UserDTO();
        if(usuario_optional.isPresent()){
            User usuario = usuario_optional.orElseThrow();
            user_dto.setUsername(usuario.getUsername());
            user_dto.setEmail(usuario.getEmail());
        }
        return user_dto;
    }
    
    @DeleteMapping("/delete/{username}")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> eliminarUsuario(@PathVariable String username){
        try {
            User usuario = userService.getUserByUsername(username);
            userService.eliminarUsuario(usuario);
            return ResponseEntity.ok("Usuario eliminado con exito");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Se produjo un error al eliminar el usuario");
        }
    }
    
    @DeleteMapping("/delete-account")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> eliminarCuenta(@RequestBody ChangePasswordRequest credentials){
        try {
            
            return ResponseEntity.ok("Cuenta eliminada con exito");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al intentar eliminar la cuenta");
        }
    }
}

