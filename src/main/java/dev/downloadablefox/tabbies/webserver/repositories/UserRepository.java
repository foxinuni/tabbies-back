package dev.downloadablefox.tabbies.webserver.repositories;

import java.util.Collection;

import org.springframework.stereotype.Repository;

import dev.downloadablefox.tabbies.webserver.entities.User;

@Repository
public interface UserRepository {
    Collection<User> findAll();
    User findById(Long id);
    void upsert(User user);
    void deleteById(Long id);
}
