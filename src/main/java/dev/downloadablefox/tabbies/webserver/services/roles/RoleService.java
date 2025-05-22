package dev.downloadablefox.tabbies.webserver.services.roles;

import org.springframework.stereotype.Service;

import dev.downloadablefox.tabbies.webserver.entities.Role;
import dev.downloadablefox.tabbies.webserver.entities.RoleType;

@Service
public interface RoleService {
    public Role getRoleById(Long id);
    public Role getRoleByType(RoleType type);
    public Role getRoleByName(String name);
    public void createRole(Role role);
}
