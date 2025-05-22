package dev.downloadablefox.tabbies.webserver.config.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import dev.downloadablefox.tabbies.webserver.entities.Medicine;
import dev.downloadablefox.tabbies.webserver.entities.Pet;
import dev.downloadablefox.tabbies.webserver.entities.Procedure;
import dev.downloadablefox.tabbies.webserver.entities.Role;
import dev.downloadablefox.tabbies.webserver.entities.RoleType;
import dev.downloadablefox.tabbies.webserver.entities.User;
import dev.downloadablefox.tabbies.webserver.entities.Veterinary;
import dev.downloadablefox.tabbies.webserver.repositories.MedicineRepository;
import dev.downloadablefox.tabbies.webserver.repositories.PetRepository;
import dev.downloadablefox.tabbies.webserver.repositories.UserRepository;
import dev.downloadablefox.tabbies.webserver.repositories.VeterinaryRepository;
import dev.downloadablefox.tabbies.webserver.services.procedure.ProcedureService;

@SpringBootTest
@Transactional
public class ProcedureServiceTest {
    @Autowired
    private ProcedureService procedureService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private VeterinaryRepository veterinaryRepository;

    @Autowired
    private PetRepository petRepository;

    @Autowired
    private MedicineRepository medicineRepository;

    private User user;
    private Veterinary veterinary;
    private Pet pet;
    private Medicine medicine;

    @BeforeEach
    public void init() {
        final Role userRole = RoleType.USER.getRole();
        final Role vetRole = RoleType.VETERINARY.getRole();

        user = new User(
            "paco" + System.currentTimeMillis() + "@gmail.com",
            "paco123",
            userRole,
            (int) (Math.random() * 1_000_000_000),
            "Paco",
            System.currentTimeMillis() 
        );
        user = userRepository.save(user);

        veterinary = new Veterinary(
            "maria" + System.currentTimeMillis() + "@tabbies.com",
            "maria123",
            vetRole,
            "Cardiología",
            "https://www.promedco.com/images/NOTICIAS_2020/reducir-estres-de-mascotas-1.jpg",
            (int) (Math.random() * 1_000_000_000), 
            "Dra. María López",
            System.currentTimeMillis()
        );
        veterinary = veterinaryRepository.save(veterinary);

        pet = new Pet(
            "Emilio",
            "Esfinge",
            LocalDate.of(2000, 1, 1),
            4.2f,
            "https://i2.wp.com/enelveterinario.com/wp-content/uploads/2021/09/post_gato_esfinge.jpg?ssl=1",
            user,
            false
        );
        pet = petRepository.save(pet);

        medicine = new Medicine("Paracetamol", 10.0, 5.0, 100, 500);
        medicine = medicineRepository.save(medicine);
    }

    @Test
    public void ProcedureService_CreateProcedure_Procedure() {
        Procedure procedure = new Procedure(3, "Resfriado", pet, medicine, veterinary);

        procedureService.createProcedure(procedure);

        Collection<Procedure> procedures = procedureService.getAllProcedures();
        Assertions.assertThat(procedures).isNotEmpty();
        Assertions.assertThat(procedures).anyMatch(p -> p.getNotes().equals("Resfriado"));
    }

    @Test
    public void ProcedureService_findAll_ProcedureList(){
        Procedure procedure = new Procedure(3, "Chequeo", pet, medicine, veterinary);
        procedureService.createProcedure(procedure);
    
        List<Procedure> procedures = new ArrayList<>(procedureService.getAllProcedures());
    
        Assertions.assertThat(procedures).isNotEmpty();
        Assertions.assertThat(procedures).hasSize(1);
    }
    
    

    @Test
    public void ProcedureService_getProcedureById_returnsProcedure() {
        Procedure procedure = new Procedure(null, 2, "Vacunación", pet, medicine, veterinary);
        procedureService.createProcedure(procedure);

        Procedure fetched = procedureService.getProcedureById(procedure.getId());

        Assertions.assertThat(fetched).isNotNull();
        Assertions.assertThat(fetched.getPet().getName()).isEqualTo("Emilio");
    }

    @Test
    public void ProcedureService_updateProcedure_updateProcedure() {

        Procedure original = new Procedure(null, 2, "Vacunación", pet, medicine, veterinary);
        procedureService.createProcedure(original);

        Procedure updated = new Procedure(null, 5, "Chequeo general", pet, medicine, veterinary);
        procedureService.updateProcedure(original.getId(), updated);

        Procedure fetched = procedureService.getProcedureById(original.getId());

        Assertions.assertThat(fetched).isNotNull();
    }
}