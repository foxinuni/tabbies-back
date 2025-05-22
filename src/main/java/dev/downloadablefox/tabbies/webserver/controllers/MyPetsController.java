package dev.downloadablefox.tabbies.webserver.controllers;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import dev.downloadablefox.tabbies.webserver.dtos.PetView;
import dev.downloadablefox.tabbies.webserver.entities.Pet;
import dev.downloadablefox.tabbies.webserver.entities.User;
import dev.downloadablefox.tabbies.webserver.services.common.ModelMapper;
import dev.downloadablefox.tabbies.webserver.services.pets.PetService;
import dev.downloadablefox.tabbies.webserver.services.user.UserService;

@Controller
@RequestMapping("/my-pets")
public class MyPetsController {
    @Autowired
    private UserService userService;
    
    @Autowired
    private PetService petService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/")
    @ResponseBody
    public Collection<PetView> myPets() {
        final User user = userService.getUserByEmail(
            SecurityContextHolder.getContext().getAuthentication().getName()
        );

        List<PetView> pets = petService.getAllPets().stream()
            .filter(pet -> pet.getOwner().getId().equals(user.getId()))
            .map(pet -> modelMapper.toPetDTO(pet))
            .toList();

        return pets;
    }

    @GetMapping("/{id}")
    @ResponseBody
    public PetView getPetById(@PathVariable Long id) {
        final User user = userService.getUserByEmail(
            SecurityContextHolder.getContext().getAuthentication().getName()
        );

        final Pet pet = petService.getPetById(id);
        if (pet == null || !pet.getOwner().getId().equals(user.getId())) {
            throw new RuntimeException("Pet not found or does not belong to user");
        }

        return modelMapper.toPetDTO(pet);
    }
}
