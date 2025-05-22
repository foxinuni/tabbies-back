package dev.downloadablefox.tabbies.webserver.services.roles;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.downloadablefox.tabbies.webserver.entities.Role;
import dev.downloadablefox.tabbies.webserver.entities.RoleType;
import dev.downloadablefox.tabbies.webserver.repositories.RoleRepository;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private final RoleRepository roleRepository;
    
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role getRoleById(Long id) {
        return roleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Role not found with id: " + id));
    }

    @Override
    public Role getRoleByType(RoleType type) {
        return roleRepository.findByName(type.getRole().getName())
                .orElseThrow(() -> new IllegalArgumentException("Role not found with type: " + type));
    }

    @Override
    public Role getRoleByName(String name) {
        return roleRepository.findByName(name)
                .orElseThrow(() -> new IllegalArgumentException("Role not found with name: " + name));
    }

    @Override
    public void createRole(Role role) {
        roleRepository.save(role);
    }
    
}
