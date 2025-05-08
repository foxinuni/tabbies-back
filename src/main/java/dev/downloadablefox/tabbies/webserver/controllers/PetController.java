package dev.downloadablefox.tabbies.webserver.controllers;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import dev.downloadablefox.tabbies.webserver.dtos.PetUpsert;
import dev.downloadablefox.tabbies.webserver.dtos.PetView;
import dev.downloadablefox.tabbies.webserver.entities.Pet;
import dev.downloadablefox.tabbies.webserver.services.common.ModelMapper;
import dev.downloadablefox.tabbies.webserver.services.pets.PetService;

@Controller
@RequestMapping("/pets")
public class PetController {

    @Autowired
    private PetService petService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/")
    @ResponseBody
    public ResponseEntity<Collection<PetView>> listPets() {
        Collection<PetView> pets = petService.getAllPets()
            .stream()
            .map(modelMapper::toPetDTO)
            .toList();
        return ResponseEntity.ok(pets);
    }

    @PostMapping("/")
    @ResponseBody
    public ResponseEntity<PetView> createPet(@RequestBody PetUpsert dto) {
        Pet pet = modelMapper.toPetEntity(dto);
        petService.createPet(pet);
        return ResponseEntity.status(HttpStatus.CREATED).body(modelMapper.toPetDTO(pet));
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<PetView> getPetById(@PathVariable Long id) {
        Pet pet = petService.getPetById(id);
        if (pet == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(modelMapper.toPetDTO(pet));
    }

    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity<PetView> updatePet(@PathVariable Long id, @RequestBody PetUpsert dto) {
        Pet pet = modelMapper.toPetEntity(dto);
        petService.updatePet(id, pet);
        return ResponseEntity.ok(modelMapper.toPetDTO(pet));
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Void> deletePet(@PathVariable Long id) {
        petService.deletePet(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/status")
    @ResponseBody
    public ResponseEntity<PetView> disablePet(@RequestParam("active") Optional<Boolean> active, @PathVariable Long id) {
        if (active.isEmpty()) {
            return ResponseEntity.ok(modelMapper.toPetDTO(petService.getPetById(id)));
        }
        petService.setActive(id, active.get());
        return ResponseEntity.ok(modelMapper.toPetDTO(petService.getPetById(id)));
    }
}