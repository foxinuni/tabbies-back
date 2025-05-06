package dev.downloadablefox.tabbies.webserver.services.auth;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import dev.downloadablefox.tabbies.webserver.entities.User;
import dev.downloadablefox.tabbies.webserver.entities.Veterinary;
import dev.downloadablefox.tabbies.webserver.repositories.UserRepository;
import dev.downloadablefox.tabbies.webserver.repositories.VeterinaryRepository;

@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private VeterinaryRepository VeterinaryRepository;

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

    @Override
    public Optional<Pair<Veterinary, String>> loginVet(String email, String password) {
        for (Veterinary vet : VeterinaryRepository.findAll()) {
            if (vet.getEmail().equals(email) && vet.getName().toLowerCase().equals(password)) {
                System.out.println("Vet found: " + vet.getName() + " " + vet.getEmail() + " " + vet.getId());

                String token = tokenService.generateToken(vet.getId());
                Pair<Veterinary, String> userTokenPair = Pair.of(vet, token);
                return Optional.of(userTokenPair);
            }
        }

        return Optional.empty();
    }

    @Override
    public Optional<Veterinary> validateVet(String token) {
        if (!tokenService.isTokenValid(token)) {
            return Optional.empty();
        }

        Long vetId = tokenService.getUserIdFromToken(token);
        if (vetId == null) {
            return Optional.empty();
        }

        return VeterinaryRepository.findById(vetId);
    }
}
