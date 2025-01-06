package mySystem.QuickSales.controller;

import mySystem.QuickSales.DTO.CreateUserRequest;
import mySystem.QuickSales.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @PostMapping("/create-user")
//    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> createUser(@RequestBody CreateUserRequest request) {
        try {
            userService.crearUsuario(request);
            return ResponseEntity.ok("Usuario creado correctamente.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error en la creacion del usuario \n"+e);
        }
    }
}
