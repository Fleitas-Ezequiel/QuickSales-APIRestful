package mySystem.QuickSales.configuration;

import jakarta.annotation.PostConstruct;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import mySystem.QuickSales.model.Role;
import mySystem.QuickSales.model.User;
import mySystem.QuickSales.repository.UserRepository;
import mySystem.QuickSales.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitializer {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleService roleService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostConstruct
    public void init() {
        // Crear roles iniciales
        Role adminRole = roleService.createRoleIfNotExists("ROLE_ADMIN");
        Role vendedorRole = roleService.createRoleIfNotExists("ROLE_VENDEDOR");

        // Crear usuario root si no existe
        if (!userRepository.existsByUsername("root")) {
            User rootUser = new User();
            rootUser.setUsername("root");
            rootUser.setEmail("admin@admin.com");
            rootUser.setPassword(passwordEncoder.encode("toor"));
            rootUser.setRole(Set.of(adminRole));
            userRepository.save(rootUser);
        }
    }
}
