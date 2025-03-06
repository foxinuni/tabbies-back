package dev.downloadablefox.tabbies.webserver.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import dev.downloadablefox.tabbies.webserver.entities.User;

@Service
public interface AuthService {
    Optional<User> login(String email, String password);
    Optional<User> validate(String token);
}
