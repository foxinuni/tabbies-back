package dev.downloadablefox.tabbies.webserver.repositories;

import java.util.Collection;

import org.springframework.stereotype.Repository;

import dev.downloadablefox.tabbies.webserver.entities.User;

@Repository
public interface UserRepository {
    Collection<User> findAllUsers();
    User findUserById(Long id);
    void upsertUser(User user);
    void deleteUserById(Long id);
}
