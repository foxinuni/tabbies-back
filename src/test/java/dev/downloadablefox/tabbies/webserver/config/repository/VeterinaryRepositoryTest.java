package dev.downloadablefox.tabbies.webserver.config.repository;


import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;


import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import dev.downloadablefox.tabbies.webserver.entities.Veterinary;
import dev.downloadablefox.tabbies.webserver.repositories.VeterinaryRepository;


@DataJpaTest
@RunWith(SpringRunner.class)
public class VeterinaryRepositoryTest {

    @Autowired
    VeterinaryRepository veterinaryRepository;

 
    @BeforeEach
    public void setUp() {
        veterinaryRepository.save(new Veterinary(
            "Veterinary", 
            "Cardiología",
            "https://www.promedco.com/images/NOTICIAS_2020/reducir-estres-de-mascetas-1.jpg",
            886333637,
            "Dra. María López",
            "maria-lopez0@tabbies.com",  
            363962911L
        ));
        
        veterinaryRepository.save(new Veterinary(
            "Veterinary2", 
            "Cardiología",
            "https://www.promedco.com/images/NOTICIAS_2020/reducir-estres-de-mascotas-1.jpg",
            886333638,  
            "Dra. María López",
            "maria-lopez1@tabbies.com"  , 
            363962916L  
        ));


        Veterinary veterinary = veterinaryRepository.findById(2L).get();
        veterinary.setName("Veterinary3");
        veterinaryRepository.save(veterinary);

        
    }


    //Test Save #1
    @Test
    public void VeterinaryRepository_save_Veterinary() {
        Veterinary veterinary = new Veterinary(
            "Veterinary",
            "Cardiología",
            "https://www.promedco.com/images/NOTICIAS_2020/reducir-estres-de-mascotas-1.jpg",
            886333639,
            "Dra. María López",
            "maria-lopez2@tabbies.com",
            363962917L
        );
        Veterinary savedVeterinary = veterinaryRepository.save(veterinary);
        Assertions.assertThat(savedVeterinary).isNotNull();
    }


    //Test Find By Email #2
    @Test
    public void VeterinaryRepositoryTest_findByEmail_email() {
        Veterinary veterinary = new Veterinary("Veterinary","Cardiología","https://www.promedco.com/images/NOTICIAS_2020/reducir-estres-de-mascotas-1.jpg",886333637,"Dra. María López","maria-lopez@tabbies.com",363962915L);

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
    


    @Test
    public void VeterinaryFindAll_NotEmptyList() {
        Veterinary veterinary = new Veterinary(
            "Veterinary",
            "Cardiología",
            "https://www.promedco.com/images/NOTICIAS_2020/reducir-estres-de-mascotas-1.jpg",
            886333640,
            "Dra. María López4",
            "maria-lopez4@tabbies.com",
            363962917L
        );
        Veterinary veterinary2 = new Veterinary(
            "Veterinary",
            "Dermatología",
            "https://www.promedco.com/images/NOTICIAS_2020/reducir-estres-de-mascotas-2.jpg",
            886333641,
            "Dra. María López6",
            "mario-lopez@tabbies.com",
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
