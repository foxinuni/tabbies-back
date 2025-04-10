package dev.downloadablefox.tabbies.webserver.services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import dev.downloadablefox.tabbies.webserver.entities.Pet;
import dev.downloadablefox.tabbies.webserver.repositories.PetRepository;

@Service
public class PetServiceImpl implements PetService {
    @Autowired
    private final PetRepository petRepository;

    public PetServiceImpl(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    @Override
    public Collection<Pet> getAllPets(){
        return petRepository.findAll();
    }

    @Override
    public Pet getPetById(Long id) {
        return petRepository.findById(id).get();
    }

    @Override
    public void createPet(Pet pet) {
        petRepository.save(pet);
    }

    @Override
    public void updatePet(Long id, Pet pet) {
        if (petRepository.existsById(id)) {
            pet.setId(id);
            petRepository.save(pet);
        }
    }

    @Override
    public void deletePet(Long id) {
        petRepository.deleteById(id);
    }

    @Override
    public void setActive(Long id, boolean active) {
        Pet pet = petRepository.findById(id).get();
        pet.setIsDisabled(!active);
        petRepository.save(pet);
    }
}
