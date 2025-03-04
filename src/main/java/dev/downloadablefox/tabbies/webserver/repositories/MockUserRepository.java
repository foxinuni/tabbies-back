package dev.downloadablefox.tabbies.webserver.repositories;

import java.util.Collection;

import dev.downloadablefox.tabbies.webserver.entities.User;
import java.util.HashMap;
import java.util.Map;

public class MockUserRepository implements UserRepository {
    private final Map<Long, User> userMap = new HashMap<>();

    @Override
    public Collection<User> findAll() {
        return userMap.values();
    }

    @Override
    public User findById(Long id) {
        return userMap.get(id);
    }

    @Override
    public void upsert(User user) {
        userMap.put(user.getId(), user);
    }

    @Override
    public void deleteById(Long id) {
        userMap.remove(id);
    }
}
