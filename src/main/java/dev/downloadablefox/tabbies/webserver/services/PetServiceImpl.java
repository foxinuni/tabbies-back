package dev.downloadablefox.tabbies.webserver.services;

import java.util.Collection;
import org.springframework.stereotype.Service;
import dev.downloadablefox.tabbies.webserver.entities.Pet;
import dev.downloadablefox.tabbies.webserver.repositories.PetRepository;

@Service
public class PetServiceImpl implements PetService {
    private final PetRepository petRepository;

    public PetServiceImpl(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    public Collection<Pet> getAllPets(){
        return petRepository.findAllPets();
    }

    public Pet getPetById(Long id) {
        return petRepository.findPetById(id);
    }

    public void createPet(Pet pet) {
        petRepository.upsertPet(pet);
    }

    public void updatePet(Long id, Pet pet) {
        petRepository.upsertPet(pet);
    }

    public void deletePet(Long id) {
        petRepository.deletePetById(id);
    }
}
