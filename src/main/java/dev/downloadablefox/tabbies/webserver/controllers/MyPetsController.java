package dev.downloadablefox.tabbies.webserver.controllers;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import dev.downloadablefox.tabbies.webserver.dtos.PetView;
import dev.downloadablefox.tabbies.webserver.entities.Pet;
import dev.downloadablefox.tabbies.webserver.entities.User;
import dev.downloadablefox.tabbies.webserver.services.AuthService;
import dev.downloadablefox.tabbies.webserver.services.ModelMapper;
import dev.downloadablefox.tabbies.webserver.services.PetService;

@Controller
@RequestMapping("/my-pets")
public class MyPetsController {
    @Autowired
    private AuthService authService;

    @Autowired
    private PetService petService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/")
    @ResponseBody
    public Collection<PetView> myPets(@CookieValue("session") Optional<String> session) {
        if (!session.isPresent()) {
            throw new RuntimeException("User is not authenticated");
        }

        final Optional<User> user = authService.validate(session.get());
        if (!user.isPresent()) {
            throw new RuntimeException("Invalid session");
        }

        List<PetView> pets = petService.getAllPets().stream()
            .filter(pet -> pet.getOwner().getId().equals(user.get().getId()))
            .map(pet -> modelMapper.toPetDTO(pet))
            .toList();

        return pets;
    }

    @GetMapping("/{id}")
    @ResponseBody
    public PetView getPetById(@CookieValue("session") Optional<String> session, @PathVariable Long id) {
        if (!session.isPresent()) {
            throw new RuntimeException("User is not authenticated");
        }

        final Optional<User> user = authService.validate(session.get());
        if (!user.isPresent()) {
            throw new RuntimeException("Invalid session");
        }

        final Pet pet = petService.getPetById(id);
        if (pet == null || !pet.getOwner().getId().equals(user.get().getId())) {
            throw new RuntimeException("Pet not found or does not belong to user");
        }

        return modelMapper.toPetDTO(pet);
    }
}
