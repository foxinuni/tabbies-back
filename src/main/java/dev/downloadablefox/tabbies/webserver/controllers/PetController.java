package dev.downloadablefox.tabbies.webserver.controllers;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import dev.downloadablefox.tabbies.webserver.dtos.PetUpsert;
import dev.downloadablefox.tabbies.webserver.dtos.PetView;
import dev.downloadablefox.tabbies.webserver.entities.Pet;
import dev.downloadablefox.tabbies.webserver.services.ModelMapper;
import dev.downloadablefox.tabbies.webserver.services.PetService;

@Controller
@RequestMapping("/pets")
public class PetController {
    @Autowired
    private PetService petService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/")
    @ResponseBody
    public Collection<PetView> listPets(Model model) {
        return petService.getAllPets()
            .stream()
            .map(modelMapper::toPetDTO)
            .toList();
    }

    @PostMapping("/")
    @ResponseBody
    public PetView createPet(@RequestBody PetUpsert dto) {
        Pet pet = modelMapper.toPetEntity(dto);
        petService.createPet(pet);
        return modelMapper.toPetDTO(pet);
    }

    @GetMapping("/{id}")
    @ResponseBody
    public PetView getPetById(@PathVariable Long id) {
        final Pet pet = petService.getPetById(id);
        return modelMapper.toPetDTO(pet);
    }
    
    @PutMapping("/{id}")
    @ResponseBody
    public PetView updatePet(@PathVariable Long id, @RequestBody PetUpsert dto) {
        Pet pet = modelMapper.toPetEntity(dto);
        petService.updatePet(id, pet);
        return modelMapper.toPetDTO(pet);
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public String deletePet(@PathVariable Long id) {
        petService.deletePet(id);
        return "redirect:/pets/";
    }

    @PostMapping("/{id}/status")
    @ResponseBody
    public PetView disablePet(@RequestParam("active") Optional<Boolean> active, @PathVariable Long id) {
        if (!active.isPresent()) {
            return modelMapper.toPetDTO(petService.getPetById(id));
        }

        petService.setActive(id, active.get());
        return modelMapper.toPetDTO(petService.getPetById(id));
    }
}
