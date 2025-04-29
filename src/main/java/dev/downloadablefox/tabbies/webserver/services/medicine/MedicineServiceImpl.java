package dev.downloadablefox.tabbies.webserver.services.medicine;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.downloadablefox.tabbies.webserver.entities.Medicine;
import dev.downloadablefox.tabbies.webserver.repositories.MedicineRepository;

@Service
public class MedicineServiceImpl implements MedicineService {
    @Autowired
    private MedicineRepository medicineRepository;

    @Override
    public Collection<Medicine> getAllMedicines() {
        return medicineRepository.findAll();
    }

    @Override
    public Medicine getMedicineById(Long id) {
        return medicineRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Medicine with ID " + id + " not found"));
    }

    @Override
    public void createMedicine(Medicine medicine) {
        medicineRepository.save(medicine);
    }

    @Override
    public void updateMedicine(Long id, Medicine medicine) {
        medicine.setId(id);
        this.medicineRepository.save(medicine);
    }

    @Override
    public void deleteMedicine(Long id) {
        if (!medicineRepository.existsById(id)) {
            throw new IllegalArgumentException("Medicine with ID " + id + " not found");
        }

        medicineRepository.deleteById(id);
    }
}
