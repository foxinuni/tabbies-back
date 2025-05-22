package dev.downloadablefox.tabbies.webserver.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.downloadablefox.tabbies.webserver.entities.UserEntity;

public interface UserEntityRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByEmail(String email);
}
