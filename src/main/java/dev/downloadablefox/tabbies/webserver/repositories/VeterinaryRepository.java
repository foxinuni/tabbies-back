package dev.downloadablefox.tabbies.webserver.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.downloadablefox.tabbies.webserver.entities.Veterinary;

@Repository
public interface VeterinaryRepository extends JpaRepository<Veterinary, Long> {
    Optional<Veterinary> findByEmail(String email); // Find a veterinary by email
    Optional<Veterinary> findByDocument(Integer document); // Find a veterinary by document
    List<Veterinary> findBySpeciality(String speciality);
        
    Optional<Veterinary> findByNumber(Long number);    


}
