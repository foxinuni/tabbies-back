package dev.downloadablefox.tabbies.webserver.services;

import java.util.Collection;

import org.springframework.stereotype.Service;

import dev.downloadablefox.tabbies.webserver.entities.User;

@Service
public interface UserService {
    public Collection<User> getAllUsers();
    public User getUserById(Long id);
    public void createUser(User user);
    public void updateUser(Long id, User user);
    public void deleteUser(Long id);
}
