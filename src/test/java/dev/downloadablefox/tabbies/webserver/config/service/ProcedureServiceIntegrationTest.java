package dev.downloadablefox.tabbies.webserver.config.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import dev.downloadablefox.tabbies.webserver.entities.*;
import dev.downloadablefox.tabbies.webserver.repositories.*;
import dev.downloadablefox.tabbies.webserver.services.procedure.*;

@DataJpaTest
@RunWith(SpringRunner.class)
public class ProcedureServiceIntegrationTest {

    @Autowired
    private ProcedureService procedureService;

    @Autowired
    private ProcedureRepository procedureRepository;

    @Autowired
    private PetRepository petRepository;

    @Autowired
    private MedicineRepository medicineRepository;

    @Autowired
    private VeterinaryRepository veterinaryRepository;

    private Pet pet;
    private Medicine med;
    private Veterinary vet;

    @Before
    public void setUp() {
        User owner = new User(123456, "Juan", "juan@example.com", "hash", 987654321L);
        pet = new Pet("Firulais", "Perro", LocalDate.of(2020, 1, 1), 20.0f, "Descripción", owner, true);
        petRepository.save(pet);

        med = new Medicine("Antibiótico", 25.0, 10.0, 100, 7);

        medicineRepository.save(med);

        vet = new Veterinary("Clínica Central", "General", "img.jpg", 123456789, "Dra. López", "vet@example.com", 123456789L);
        veterinaryRepository.save(vet);
    }

    @Test
    public void ProcedureService_createProcedure_procedure() {
        Procedure procedure1 = new Procedure(1, "Chequeo", pet, med, vet);
        Procedure procedure2 = new Procedure(2, "Revisión Dental", pet, med, vet);

        procedureService.createProcedure(procedure1);
        procedureService.createProcedure(procedure2);

        assertThat(procedureRepository.findAll()).hasSize(2);
    }
}