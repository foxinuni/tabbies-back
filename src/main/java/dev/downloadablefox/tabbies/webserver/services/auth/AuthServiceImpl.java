package dev.downloadablefox.tabbies.webserver.services.auth;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import dev.downloadablefox.tabbies.webserver.entities.User;
import dev.downloadablefox.tabbies.webserver.repositories.UserRepository;

@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenService tokenService;

    @Override
    public Optional<Pair<User, String>> login(String email, String password) {
        for (User user : userRepository.findAll()) {
            if (user.getEmail().equals(email) && user.getHash().equals(password)) {
                String token = tokenService.generateToken(user.getId());
                Pair<User, String> userTokenPair = Pair.of(user, token);
                return Optional.of(userTokenPair);
            }
        }

        return Optional.empty();
    }

    @Override
    public Optional<User> validate(String token) {
        if (!tokenService.isTokenValid(token)) {
            return Optional.empty();
        }

        Long userId = tokenService.getUserIdFromToken(token);
        if (userId == null) {
            return Optional.empty();
        }

        return userRepository.findById(userId);
    }
}
