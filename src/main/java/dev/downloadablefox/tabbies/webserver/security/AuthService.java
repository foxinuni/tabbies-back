package dev.downloadablefox.tabbies.webserver.security;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import dev.downloadablefox.tabbies.webserver.entities.Role;
import dev.downloadablefox.tabbies.webserver.entities.UserEntity;
import dev.downloadablefox.tabbies.webserver.repositories.UserEntityRepository;

@Service
public class AuthService implements UserDetailsService {
    @Autowired
    private UserEntityRepository userEntityRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity userEntity = userEntityRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));

        UserDetails userDetails = new User(
            userEntity.getEmail(),
            userEntity.getHash(),
            mapRoleToAuthorities(userEntity.getRole())
        );

        return userDetails;
    }

    private Collection<GrantedAuthority> mapRoleToAuthorities(Role role) {
        return List.of(new SimpleGrantedAuthority(role.getName()));
    }
}
