package dev.downloadablefox.tabbies.webserver.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import dev.downloadablefox.tabbies.webserver.entities.Pet;
import dev.downloadablefox.tabbies.webserver.entities.User;
import dev.downloadablefox.tabbies.webserver.services.AuthService;
import dev.downloadablefox.tabbies.webserver.services.PetService;

@Controller
@RequestMapping("/my-pets")
public class MyPetsController {
    @Autowired
    private AuthService authService;

    @Autowired
    private PetService petService;

    @GetMapping("/")
    public String myPets(@CookieValue("session") Optional<String> session, Model model) {
        if (!session.isPresent()) {
            System.out.println("User is not authenticated");
            return "redirect:/auth/login";
        }

        final Optional<User> user = authService.validate(session.get());
        if (!user.isPresent()) {
            return "redirect:/auth/login";
        }

        List<Pet> pets = petService.getAllPets().stream()
            .filter(pet -> pet.getOwner().getId().equals(user.get().getId()))
            .toList();

        model.addAttribute("pets", pets);
        return "my-pets/my-pets";
    }

    @GetMapping("/{id}")
    public String getPetById(@CookieValue("session") Optional<String> session, @PathVariable Long id, Model model) {
        if (!session.isPresent()) {
            System.out.println("User is not authenticated");
            return "redirect:/auth/login";
        }

        final Optional<User> user = authService.validate(session.get());
        if (!user.isPresent()) {
            return "redirect:/auth/login";
        }

        final Pet pet = petService.getPetById(id);

        model.addAttribute("pet", pet);
        model.addAttribute("owner", user.get());
        return "my-pets/my-pet-details";
    }
    
}
