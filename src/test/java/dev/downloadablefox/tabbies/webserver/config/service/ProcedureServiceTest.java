package dev.downloadablefox.tabbies.webserver.config.service;

import dev.downloadablefox.tabbies.webserver.entities.*;
import dev.downloadablefox.tabbies.webserver.services.procedure.ProcedureService;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;


@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
public class ProcedureServiceTest {

    @Autowired
    private ProcedureService procedureService;

    private User user;
    private Veterinary veterinary;
    private Pet pet;
    private Medicine medicine;

    @Before
    public void setUp() {
        user = new User(123456789, "Paco", "Paco@gmail.com", "paco", 123456789L);
        veterinary = new Veterinary(
            "Veterinary", 
            "Cardiología", 
            "https://www.promedco.com/images/NOTICIAS_2020/reducir-estres-de-mascotas-1.jpg", 
            886333637, 
            "Dra. María López", 
            "maria-lopez@tabbies.com", 
            363962915L
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

        Procedure procedure = new Procedure(123456789L, 1, "Resfriado", pet, medicine, veterinary);
        Procedure procedure2 = new Procedure(123456782L, 2, "Intoxicación", pet, medicine, veterinary);

        


    }



   
}