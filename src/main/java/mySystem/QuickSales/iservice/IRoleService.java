package mySystem.QuickSales.iservice;

import java.util.List;
import mySystem.QuickSales.model.Role;

public interface IRoleService {
    
    public void crearRol(Role rol);
    public Role createRoleIfNotExists(String roleName);
    public void modificarRol(Role rol);
    public void eliminarRol(Role rol);
    public Role getRoleByName(String roleName);
    public List<Role> getRoleByUsername(String username);
    public List<Role> getAllRoles();
}
