package dev.downloadablefox.tabbies.webserver.services;

import java.util.Collection;

import org.springframework.stereotype.Service;

import dev.downloadablefox.tabbies.webserver.entities.Pet;

@Service
public interface PetService {
    public Collection<Pet> getAllPets();
    public Pet getPetById(Long id);
    public void createPet(Pet pet);
    public void updatePet(Long id, Pet pet);
    public void deletePet(Long id);
    public void setActive(Long id, boolean active);
}
