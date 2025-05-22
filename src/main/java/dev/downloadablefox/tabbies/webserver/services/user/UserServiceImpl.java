package dev.downloadablefox.tabbies.webserver.services.user;

import java.util.Collection;

import org.springframework.stereotype.Service;

import dev.downloadablefox.tabbies.webserver.entities.User;
import dev.downloadablefox.tabbies.webserver.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Collection<User> getAllUsers(){
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).get();
    }

    public void createUser(User user) {
        userRepository.save(user);
    }

    public void updateUser(Long id, User user) {
        User existingUser = userRepository.findById(id).get();
        if (existingUser == null) {
            throw new IllegalArgumentException("User not found with id: " + id);
        }
        
        user.setId(id);
        user.setPets(existingUser.getPets());

        userRepository.save(user);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User getUserByEmail(String name) {
        return userRepository.findByEmail(name)
                .orElseThrow(() -> new IllegalArgumentException("User not found with email: " + name));
    }
}
