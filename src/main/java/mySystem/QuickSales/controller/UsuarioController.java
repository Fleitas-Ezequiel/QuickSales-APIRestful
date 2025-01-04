package mySystem.QuickSales.controller;

import java.util.List;
import lombok.AllArgsConstructor;
import mySystem.QuickSales.DTO.ChangePasswordRequest;
import mySystem.QuickSales.DTO.UserDTO;
import mySystem.QuickSales.iservice.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
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
    @PreAuthorize("hasRole('ADMIN')")
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
            return (List<UserDTO>) ResponseEntity.badRequest().body("Error al solicitar los datos");
        }
    }
}

