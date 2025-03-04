package dev.downloadablefox.tabbies.webserver.repositories;

import java.util.Collection;

import org.springframework.stereotype.Repository;

import dev.downloadablefox.tabbies.webserver.entities.Pet;

@Repository
public interface PetRepository {
    Collection<Pet> findAllPets();
    Pet findPetById(Long id);
    void upsertPet(Pet pet);
    void deletePetById(Long id);
}
