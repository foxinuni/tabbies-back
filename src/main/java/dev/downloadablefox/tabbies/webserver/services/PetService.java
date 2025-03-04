package dev.downloadablefox.tabbies.webserver.services;

import java.util.Collection;
import org.springframework.stereotype.Service;
import dev.downloadablefox.tabbies.webserver.entities.Pet;
import dev.downloadablefox.tabbies.webserver.repositories.PetRepository;

@Service
public class PetService {
    private final PetRepository petRepository;

    public PetService(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    public Collection<Pet> getAllPets(){
        return petRepository.findAll();
    }

    public Pet getPetById(Long id) {
        return petRepository.findById(id);
    }

    public void createPet(Pet pet) {
        petRepository.upsert(pet);
    }

    public void updatePet(Long id, Pet pet) {
        petRepository.upsert(pet);
    }

    public void deletePet(Long id) {
        petRepository.deleteById(id);
    }
}
