package dev.downloadablefox.tabbies.webserver.controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import dev.downloadablefox.tabbies.webserver.dtos.VeterinarianUpsert;
import dev.downloadablefox.tabbies.webserver.dtos.VeterinarianView;
import dev.downloadablefox.tabbies.webserver.entities.Veterinary;
import dev.downloadablefox.tabbies.webserver.services.common.ModelMapper;
import dev.downloadablefox.tabbies.webserver.services.veterinary.VeterinarianService;

@Controller
@RequestMapping("/veterinarians")
public class VeterinarianController {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private VeterinarianService veterinarianService;


    @GetMapping("/")
    @ResponseBody
    public Collection<VeterinarianView> listVeterinarians(Model model) {
        return veterinarianService.getAllVeterinarians()
            .stream()
            .map(modelMapper::toVeterinaryDTO)
            .toList();
    }

    @PostMapping("/")
    @ResponseBody
    public VeterinarianView createVeterinarian(@RequestBody VeterinarianUpsert dto) {
        Veterinary veterinarian = modelMapper.toVeterinaryEntity(dto);
        veterinarianService.createVeterinarian(veterinarian);
        return modelMapper.toVeterinaryDTO(veterinarian);
    }

    @GetMapping("/{id}")
    @ResponseBody
    public VeterinarianView getVeterinarianById(@PathVariable Long id) {
        Veterinary veterinary = veterinarianService.getVeterinarianById(id);
        return modelMapper.toVeterinaryDTO(veterinary);
    }
    
    @PutMapping("/{id}")
    @ResponseBody
    public VeterinarianView updateVeterinarian(@PathVariable Long id, @RequestBody VeterinarianUpsert dto) {
        Veterinary veterinary = modelMapper.toVeterinaryEntity(dto);
        veterinarianService.updateVeterinarian(id, veterinary);
        return modelMapper.toVeterinaryDTO(veterinary);
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        veterinarianService.deleteVeterinarian(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/@me")
    @ResponseBody
    public VeterinarianView getCurrentVeterinarian() {
        final Veterinary veterinary = veterinarianService.getVeterinarianByEmail(
            SecurityContextHolder.getContext().getAuthentication().getName()
        );

        return modelMapper.toVeterinaryDTO(veterinary);
    }
}
