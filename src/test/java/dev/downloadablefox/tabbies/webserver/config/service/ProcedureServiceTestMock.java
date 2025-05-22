package dev.downloadablefox.tabbies.webserver.config.service;

import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import dev.downloadablefox.tabbies.webserver.entities.Medicine;
import dev.downloadablefox.tabbies.webserver.entities.Pet;
import dev.downloadablefox.tabbies.webserver.entities.Procedure;
import dev.downloadablefox.tabbies.webserver.entities.Role;
import dev.downloadablefox.tabbies.webserver.entities.RoleType;
import dev.downloadablefox.tabbies.webserver.entities.User;
import dev.downloadablefox.tabbies.webserver.entities.Veterinary;
import dev.downloadablefox.tabbies.webserver.repositories.MedicineRepository;
import dev.downloadablefox.tabbies.webserver.repositories.PetRepository;
import dev.downloadablefox.tabbies.webserver.repositories.ProcedureRepository;
import dev.downloadablefox.tabbies.webserver.repositories.UserRepository;
import dev.downloadablefox.tabbies.webserver.repositories.VeterinaryRepository;
import dev.downloadablefox.tabbies.webserver.services.procedure.ProcedureServiceImpl;

@ExtendWith(MockitoExtension.class)
public class ProcedureServiceTestMock {
    @InjectMocks
    private ProcedureServiceImpl procedureService;

    @Mock
    private ProcedureRepository procedureRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private VeterinaryRepository veterinaryRepository;

    @Mock
    private PetRepository petRepository;

    @Mock
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

        pet = new Pet(
            "Emilio",
            "Esfinge",
            LocalDate.of(2000, 1, 1),
            4.2f,
            "https://i2.wp.com/enelveterinario.com/wp-content/uploads/2021/09/post_gato_esfinge.jpg?ssl=1",
            user,
            false
        );
        
        medicine = new Medicine("Paracetamol", 10.0, 5.0, 100, 500);
    }

    @Test
    public void ProcedureService_CreateProcedure_Procedure() {
        Procedure procedure = new Procedure(3, "Resfriado", pet, medicine, veterinary);
        when(procedureRepository.save(any(Procedure.class))).thenReturn(procedure);

        procedureService.createProcedure(procedure);

        verify(procedureRepository, times(1)).save(procedure);
        when(procedureRepository.findAll()).thenReturn(List.of(procedure));

        List<Procedure> procedures = (List<Procedure>) procedureService.getAllProcedures();

        Assertions.assertThat(procedures).isNotEmpty();
        Assertions.assertThat(procedures).anyMatch(p -> p.getNotes().equals("Resfriado"));
    }

    @Test
    public void ProcedureService_findAll_ProcedureList() {
        when(procedureRepository.findAll()).thenReturn(List.of(
            new Procedure(3, "Chequeo", pet, medicine, veterinary)
        ));

        List<Procedure> procedures = (List<Procedure>) procedureService.getAllProcedures(); // Avoid unnecessary casting

        Assertions.assertThat(procedures)
                  .isNotEmpty()
                  .hasSize(1)
                  .anyMatch(p -> p.getNotes().equals("Chequeo")); // Chain assertions for readability
    }

    @Test
    public void ProcedureService_getProcedureById_returnsProcedure() {
        Procedure procedure = new Procedure(1, "Vacunación", pet, medicine, veterinary);
        when(procedureRepository.findById(procedure.getId())).thenReturn(java.util.Optional.of(procedure));

        Procedure fetched = procedureService.getProcedureById(procedure.getId());

        Assertions.assertThat(fetched).isNotNull();
        Assertions.assertThat(fetched.getNotes()).isEqualTo("Vacunación");
        Assertions.assertThat(fetched.getPet().getName()).isEqualTo("Emilio");

        verify(procedureRepository, times(1)).findById(procedure.getId());
    }

    @Test
    public void ProcedureService_updateProcedure_updateProcedure() {
        Procedure original = new Procedure(1, "Vacunación", pet, medicine, veterinary);
        Procedure updated = new Procedure(1, "Chequeo general", pet, medicine, veterinary);

        when(procedureRepository.existsById(original.getId())).thenReturn(true);
        when(procedureRepository.findById(original.getId())).thenReturn(Optional.of(updated));
        when(procedureRepository.save(any(Procedure.class))).thenReturn(updated);

        procedureService.updateProcedure(original.getId(), updated);

        verify(procedureRepository, times(1)).existsById(original.getId());
        verify(procedureRepository, times(1)).save(updated);

        Procedure fetched = procedureService.getProcedureById(original.getId());

        Assertions.assertThat(fetched).isNotNull();
        Assertions.assertThat(fetched.getNotes()).isEqualTo("Chequeo general");
    }
}
