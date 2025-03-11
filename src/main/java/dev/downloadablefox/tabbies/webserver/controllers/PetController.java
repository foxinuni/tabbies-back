package dev.downloadablefox.tabbies.webserver.controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import dev.downloadablefox.tabbies.webserver.entities.Pet;
import dev.downloadablefox.tabbies.webserver.entities.User;
import dev.downloadablefox.tabbies.webserver.services.PetService;
import dev.downloadablefox.tabbies.webserver.services.UserService;

@Controller
@RequestMapping("/pets")
public class PetController {
    @Autowired
    private PetService petService;

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String listPets(Model model) {
        Collection<Pet> pets = petService.getAllPets();
        model.addAttribute("pets", pets);
        return "pets/pets";
    }

    @GetMapping("/new")
    public String newPet(Model model) {
        final Collection<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        model.addAttribute("pet", new Pet());

        return "pets/pet-create";
    }

    @PostMapping("/new")
    public String createPet(Pet pet) {
        petService.createPet(pet);
        return "redirect:/pets/";
    }

    @GetMapping("/{id}")
    public String getPetById(@PathVariable Long id, Model model) {
        final Pet pet = petService.getPetById(id);
        final User owner = userService.getUserById(pet.getOwner().getId());

        model.addAttribute("pet", pet);
        model.addAttribute("owner", owner);
        return "pets/pet-details";
    }
    

    @GetMapping("/{id}/edit")
    public String editPet(@PathVariable Long id, Model model) {
        final Pet pet = petService.getPetById(id);
        final Collection<User> users = userService.getAllUsers();

        model.addAttribute("pet", pet);
        model.addAttribute("users", users);
        return "pets/pet-edit";
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
