package mySystem.QuickSales.configuration;

import jakarta.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
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

      List<Role> lista_roles = new ArrayList();
      lista_roles.add(adminRole);
      // Crear usuario root si no existe
      if (!userRepository.existsByUsername("root")) {
        User rootUser = new User();
        rootUser.setUsername("root");
        rootUser.setEmail("admin@admin.com");
        rootUser.setPassword(passwordEncoder.encode("toor"));
        rootUser.setRoles(lista_roles);
        userRepository.save(rootUser);
      }
    }
}
