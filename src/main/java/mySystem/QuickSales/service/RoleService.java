package mySystem.QuickSales.service;

import java.util.List;
import mySystem.QuickSales.iservice.IRoleService;
import mySystem.QuickSales.model.Role;
import mySystem.QuickSales.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService implements IRoleService{
    
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public void crearRol(Role rol) {
        roleRepository.save(rol);
    }
    
    @Override
    public Role createRoleIfNotExists(String roleName) {
        Role rol = new Role();
        rol.setName(roleName);
        return roleRepository.findByName(roleName)
                .orElseGet(() -> roleRepository.save(rol));
    }

    @Override
    public void modificarRol(Role rol) {
    }

    @Override
    public void eliminarRol(Role rol) {
    }

    @Override
    public Role getRoleByName(String roleName) {
        return roleRepository.findByName(roleName)
                .orElseThrow(() -> new RuntimeException("Rol no encontrado: " + roleName));
    }

    @Override
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    @Override
    public List<Role> getRoleByUsername(String username) {
        List<Role> roles = roleRepository.findByUsername(username);
        return roles;
    }
    
}
