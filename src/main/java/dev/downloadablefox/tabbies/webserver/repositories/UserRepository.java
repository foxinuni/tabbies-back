package dev.downloadablefox.tabbies.webserver.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.downloadablefox.tabbies.webserver.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{


}
