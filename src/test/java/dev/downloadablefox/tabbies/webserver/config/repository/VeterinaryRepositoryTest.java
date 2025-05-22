package dev.downloadablefox.tabbies.webserver.config.repository;

import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import dev.downloadablefox.tabbies.webserver.entities.Role;
import dev.downloadablefox.tabbies.webserver.entities.RoleType;
import dev.downloadablefox.tabbies.webserver.entities.Veterinary;
import dev.downloadablefox.tabbies.webserver.repositories.VeterinaryRepository;
import dev.downloadablefox.tabbies.webserver.services.roles.RoleService;

@DataJpaTest
public class VeterinaryRepositoryTest {
    @Autowired
    VeterinaryRepository veterinaryRepository;

    @Autowired
    RoleService roleService;

    @BeforeEach
    public void setUp() {
        roleService.createRole(RoleType.USER.getRole());
        roleService.createRole(RoleType.VETERINARY.getRole());
        roleService.createRole(RoleType.ADMIN.getRole());

        final Role veterinaryRole = roleService.getRoleByType(RoleType.VETERINARY);

        veterinaryRepository.save(new Veterinary(
            "maria-lopez0@tabbies.com",
            "maria",
            veterinaryRole,
            "Cardiología",
            "https://www.promedco.com/images/NOTICIAS_2020/reducir-estres-de-mascetas-1.jpg",
            886333637,
            "Dra. María López 1",
            363962911L
        ));
        
        veterinaryRepository.save(new Veterinary(
            "maria-lopez1@tabbies.com", 
            "maria",
            veterinaryRole,
            "Cardiología",
            "https://www.promedco.com/images/NOTICIAS_2020/reducir-estres-de-mascotas-1.jpg",
            886333638,  
            "Dra. María López 2",
            363962916L  
        ));
    }


    //create test
    @Test
    public void VeterinaryRepository_save_Veterinary() {
        final Role veterinaryRole = roleService.getRoleByType(RoleType.VETERINARY);

        Veterinary veterinary = new Veterinary(
            "maria-lopez2@tabbies.com",
            "maria",
            veterinaryRole,
            "Cardiología",
            "https://www.promedco.com/images/NOTICIAS_2020/reducir-estres-de-mascotas-1.jpg",
            886333639,
            "Dra. María López",
            363962917L
        );
        Veterinary savedVeterinary = veterinaryRepository.save(veterinary);
        Assertions.assertThat(savedVeterinary).isNotNull();
    }

    @Test
    public void VeterinaryRepositoryTest_findByEmail_email() {
        final Role veterinaryRole = roleService.getRoleByType(RoleType.VETERINARY);
        Veterinary veterinary = new Veterinary(
            "maria-lopez@tabbies.com",
            "maria",
            veterinaryRole,
            "Cardiología",
            "https://www.promedco.com/images/NOTICIAS_2020/reducir-estres-de-mascotas-1.jpg",
            886333637,
            "Dra. María López",
            363962915L
        );

        Veterinary savedVeterinary = veterinaryRepository.save(veterinary);

        Assertions.assertThat(savedVeterinary).isNotNull();
        
    }

    //test findByDocument test #3
    @Test
    public void VeterinaryRepository_findByDocument_Document() {
        Integer document = 886333637;
        Optional<Veterinary> veterinary = veterinaryRepository.findByDocument(document);
            
        Assertions.assertThat(veterinary).isNotNull();
    }

    //test findBySpeciality #4
    @Test
    public void VeterinaryRepository_findBySpeciality_ReturnsList() {
        List<Veterinary> cardiologyVeterinaries = veterinaryRepository.findBySpeciality("Cardiología");

        Assertions.assertThat(cardiologyVeterinaries).isNotEmpty();
        Assertions.assertThat(cardiologyVeterinaries.get(0).getSpeciality()).isEqualTo("Cardiología");
    }

    //test #5
    @Test
    public void VeterinaryFindAll_NotEmptyList() {
        final Role veterinaryRole = roleService.getRoleByType(RoleType.VETERINARY);
        Veterinary veterinary = new Veterinary(
            "maria-lopez4@tabbies.com",
            "maria",
            veterinaryRole,
            "Cardiología",
            "https://www.promedco.com/images/NOTICIAS_2020/reducir-estres-de-mascotas-1.jpg",
            886333640,
            "Dra. María López4",
            363962917L
        );

        Veterinary veterinary2 = new Veterinary(
            "mario-lopez@tabbies.com",
            "mario",
            veterinaryRole,
            "Dermatología",
            "https://www.promedco.com/images/NOTICIAS_2020/reducir-estres-de-mascotas-2.jpg",
            886333641,
            "Dra. María López6",
            363962918L
        );

        veterinaryRepository.save(veterinary);
        veterinaryRepository.save(veterinary2);
        List<Veterinary> veterinaries = veterinaryRepository.findAll();

        Assertions.assertThat(veterinaries).isNotNull();
        Assertions.assertThat(veterinaries.size()).isGreaterThanOrEqualTo(4); 
    }

    @Test
    public void VeterinaryRepository_findByID_FindWrongIndex() {
        Long index = 99L;

        Optional<Veterinary> veterinary = veterinaryRepository.findById(index);
        
        Assertions.assertThat(veterinary).isEmpty();
    }

    @Test
    public void VeterinaryRepository_deleteById_EmptyVeterinary() {
        Long index = 1L;
        veterinaryRepository.deleteById(index);
        Optional<Veterinary> veterinary = veterinaryRepository.findById(index);
        
        Assertions.assertThat(veterinary).isEmpty();
    }


    @Test
    public void VeterinaryRepository_UpdateVeterinary_document() {
        Integer document = 886333637;

        Veterinary veterinary = veterinaryRepository.findByDocument(document).get();
        
        veterinary.setDocument(886333610);
        veterinaryRepository.save(veterinary);
    }
}
