package dev.downloadablefox.tabbies.webserver.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.downloadablefox.tabbies.webserver.entities.User;
import dev.downloadablefox.tabbies.webserver.repositories.UserRepository;

@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public Optional<User> login(String email, String password) {
        for (User user : userRepository.findAll()) {
            if (user.getEmail().equals(email) && user.getHash().equals(password)) {
                return Optional.of(user);
            }
        }

        return Optional.empty();
    }

    @Override
    public Optional<User> validate(String token) {
        for (User user : userRepository.findAll()) {
            if (user.getEmail().equals(token)) {
                return Optional.of(user);
            }
        }

        return Optional.empty();
    }
}
