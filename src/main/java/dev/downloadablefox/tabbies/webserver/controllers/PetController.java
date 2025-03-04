package dev.downloadablefox.tabbies.webserver.controllers;

import java.util.Collection;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import dev.downloadablefox.tabbies.webserver.entities.Pet;
import dev.downloadablefox.tabbies.webserver.services.PetService;

@Controller
@RequestMapping("/pets")
public class PetController {
    private final PetService petService;

    public PetController(PetService petService) {
        this.petService = petService;
    }

    @GetMapping("/")
    public String listPets(Model model) {
        Collection<Pet> pets = petService.getAllPets();
        model.addAttribute("pets", pets);
        return "pets/pets";
    }

    @GetMapping("/new")
    public String newPet() {
        return "pets/new-pet";
    }

    @PostMapping("/new")
    public String createPet(Pet pet) {
        petService.createPet(pet);
        return "redirect:/pets";
    }

    @GetMapping("/{id}")
    public String getPetById(@PathVariable Long id, Model model) {
        Pet pet = petService.getPetById(id);
        model.addAttribute("pet", pet);
        return "pets/pet-details";
    }
    

    @GetMapping("/{id}/edit")
    public String editPet(@PathVariable Long id, Model model) {
        Pet pet = petService.getPetById(id);
        model.addAttribute("pet", pet);
        return "pets/edit-pet";
    }

    @PostMapping("/{id}/edit")
    public String updatePet(@PathVariable Long id, Pet pet) {
        petService.updatePet(id, pet);
        return "redirect:/pets/" + id;
    }

    @PostMapping("/{id}/delete")
    public String deletePet(@PathVariable Long id) {
        petService.deletePet(id);
        return "redirect:/pets/";
    }
}
