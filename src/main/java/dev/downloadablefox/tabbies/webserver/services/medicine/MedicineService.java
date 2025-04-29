package dev.downloadablefox.tabbies.webserver.services.medicine;

import java.util.Collection;

import org.springframework.stereotype.Service;

import dev.downloadablefox.tabbies.webserver.entities.Medicine;

@Service
public interface MedicineService {
    public Collection<Medicine> getAllMedicines();
    public Medicine getMedicineById(Long id);
    public void createMedicine(Medicine medicine);
    public void updateMedicine(Long id, Medicine medicine);
    public void deleteMedicine(Long id);
}