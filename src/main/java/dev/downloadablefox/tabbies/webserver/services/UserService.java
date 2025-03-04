package dev.downloadablefox.tabbies.webserver.services;

import java.util.Collection;

import org.springframework.stereotype.Service;

import dev.downloadablefox.tabbies.webserver.entities.User;
import dev.downloadablefox.tabbies.webserver.repositories.UserRepository;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Collection<User> getAllUsers(){
        return userRepository.findAllUsers();
    }

    public User getUserById(Long id) {
        return userRepository.findUserById(id);
    }

    public void createUser(User user) {
        userRepository.upsertUser(user);
    }

    public void updateUser(Long id, User user) {
        userRepository.upsertUser(user);
    }

    public void deleteUser(Long id) {
        userRepository.deleteUserById(id);
    }
}
