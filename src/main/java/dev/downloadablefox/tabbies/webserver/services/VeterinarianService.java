package dev.downloadablefox.tabbies.webserver.services;

import java.util.Collection;

import org.springframework.stereotype.Service;

import dev.downloadablefox.tabbies.webserver.entities.Veterinary;

@Service
public interface VeterinarianService {
    Collection<Veterinary> getAllVeterinarians();
    Veterinary getVeterinarianById(Long id);
    Veterinary getVeterinarianByEmail(String email);
    Veterinary getVeterinarianByDocument(Integer document);
    void createVeterinarian(Veterinary veterinarian);
    void updateVeterinarian(Long id, Veterinary veterinarian);
    void deleteVeterinarian(Long id);
}
