package dev.downloadablefox.tabbies.webserver.controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import dev.downloadablefox.tabbies.webserver.dtos.MedicineUpsert;
import dev.downloadablefox.tabbies.webserver.dtos.MedicineView;
import dev.downloadablefox.tabbies.webserver.entities.Medicine;
import dev.downloadablefox.tabbies.webserver.services.common.ModelMapper;
import dev.downloadablefox.tabbies.webserver.services.medicine.MedicineService;

@Controller
@RequestMapping("/medicines")
public class MedicineController {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private MedicineService medicineService;

    @GetMapping("/")
    @ResponseBody
    public Collection<MedicineView> listUsers() {
        return medicineService.getAllMedicines()
            .stream()
            .map(modelMapper::toMedicineDTO)
            .toList();
    }

    @PostMapping("/")
    @ResponseBody
    public MedicineView createUser(@RequestBody MedicineUpsert dto) {
        Medicine medicine = modelMapper.toMedicineEntity(dto);
        medicineService.createMedicine(medicine);
        return modelMapper.toMedicineDTO(medicine);
    }

    @GetMapping("/{id}")
    @ResponseBody
    public MedicineView getUserById(@PathVariable Long id) {
        Medicine medicine = medicineService.getMedicineById(id);
        return modelMapper.toMedicineDTO(medicine);
    }
    
    @PutMapping("/{id}")
    public MedicineView updateUser(@PathVariable Long id, @RequestBody MedicineUpsert dto) {
        Medicine medicine = modelMapper.toMedicineEntity(dto);
        medicineService.updateMedicine(id, medicine);
        return modelMapper.toMedicineDTO(medicine);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        medicineService.deleteMedicine(id);
        return ResponseEntity.noContent().build();
    }
}


