package dev.downloadablefox.tabbies.webserver.repositories;

import java.util.Collection;

import org.springframework.stereotype.Repository;

import dev.downloadablefox.tabbies.webserver.entities.Pet;

@Repository
public interface PetRepository {
    Collection<Pet> findAll();
    Pet findById(Long id);
    void upsert(Pet pet);
    void deleteById(Long id);
}
