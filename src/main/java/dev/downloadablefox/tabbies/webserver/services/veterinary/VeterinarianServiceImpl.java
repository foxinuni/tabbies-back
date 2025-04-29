package dev.downloadablefox.tabbies.webserver.services.veterinary;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.downloadablefox.tabbies.webserver.entities.Veterinary;
import dev.downloadablefox.tabbies.webserver.repositories.VeterinaryRepository;

@Service
public class VeterinarianServiceImpl implements VeterinarianService {
    @Autowired
    private VeterinaryRepository veterinaryRepository;

    @Override
    public Collection<Veterinary> getAllVeterinarians() {
        return veterinaryRepository.findAll();
    }

    @Override
    public Veterinary getVeterinarianById(Long id) {
        return veterinaryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Veterinarian not found with id: " + id));
    }

    @Override
    public Veterinary getVeterinarianByEmail(String email) {
        return veterinaryRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("Veterinarian not found with email: " + email));
    }

    @Override
    public Veterinary getVeterinarianByDocument(Integer document) {
        return veterinaryRepository.findByDocument(document)
                .orElseThrow(() -> new IllegalArgumentException("Veterinarian not found with document: " + document));
    }

    @Override
    public void createVeterinarian(Veterinary veterinarian) {
        veterinaryRepository.save(veterinarian);
    }

    @Override
    public void updateVeterinarian(Long id, Veterinary veterinarian) {
        if (!veterinaryRepository.existsById(id)) {
            throw new IllegalArgumentException("Veterinarian not found with id: " + veterinarian.getId());
        }

        veterinarian.setId(id);
        veterinaryRepository.save(veterinarian);
    }

    @Override
    public void deleteVeterinarian(Long id) {
        if (!veterinaryRepository.existsById(id)) {
            throw new IllegalArgumentException("Veterinarian not found with id: " + id);
        }
        
        veterinaryRepository.deleteById(id);
    }


}
