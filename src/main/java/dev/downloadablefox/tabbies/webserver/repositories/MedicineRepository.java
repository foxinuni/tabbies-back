package dev.downloadablefox.tabbies.webserver.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.downloadablefox.tabbies.webserver.entities.Medicine;

@Repository
public interface MedicineRepository extends JpaRepository<Medicine, Long> {
    
}
