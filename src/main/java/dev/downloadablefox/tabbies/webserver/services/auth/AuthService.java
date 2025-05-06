package dev.downloadablefox.tabbies.webserver.services.auth;

import java.util.Optional;

import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import dev.downloadablefox.tabbies.webserver.entities.User;
import dev.downloadablefox.tabbies.webserver.entities.Veterinary;

@Service
public interface AuthService {
    Optional<Pair<User, String>> login(String email, String password);
    Optional<User> validate(String token);
    
    Optional<Pair<Veterinary, String>> loginVet(String email, String password);
    Optional<Veterinary> validateVet(String token);
}
