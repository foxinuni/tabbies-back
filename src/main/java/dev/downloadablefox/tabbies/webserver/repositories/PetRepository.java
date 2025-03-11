package dev.downloadablefox.tabbies.webserver.repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.downloadablefox.tabbies.webserver.entities.Pet;

@Repository
public interface PetRepository extends JpaRepository<Pet, Long>{
    
}
