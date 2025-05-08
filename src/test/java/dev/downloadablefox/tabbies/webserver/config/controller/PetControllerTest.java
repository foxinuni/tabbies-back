package dev.downloadablefox.tabbies.webserver.config.controller;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.fasterxml.jackson.databind.ObjectMapper;

import dev.downloadablefox.tabbies.webserver.controllers.PetController;
import dev.downloadablefox.tabbies.webserver.entities.Pet;
import dev.downloadablefox.tabbies.webserver.entities.User;
import dev.downloadablefox.tabbies.webserver.repositories.UserRepository;
import dev.downloadablefox.tabbies.webserver.repositories.VeterinaryRepository;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import dev.downloadablefox.tabbies.webserver.services.common.ModelMapper;
import dev.downloadablefox.tabbies.webserver.services.pets.PetService;

//pruebas unitarias de la clase PetController
//pruebas integracion de la clase PetController

@WebMvcTest(controllers = PetController.class)
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@EnableAutoConfiguration(exclude = {org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration.class})
public class PetControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private PetService petService;

    @MockBean
    private ModelMapper modelMapper;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private VeterinaryRepository veterinaryRepository; // Mockea cualquier dependencia adicional

    @Test
    public void testCreatePet() throws Exception {
        // Crear un usuario mock
        User alfredo = new User(987654321, "Alfredo", "alfredo@gmail.com", "alfredo", 321623232L);
        when(userRepository.save(alfredo)).thenReturn(alfredo);

        // Crear una mascota mock
        Pet pet = new Pet(
                "bruno",
                "esfinge",
                LocalDate.of(2017, 2, 26),
                5.0f,
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQzkmFUIlGYeJWHDlVQXoZEHmLTnz6BcbErmw&s",
                alfredo,
                false
        );

        // Mockear el servicio para crear la mascota
        Mockito.doNothing().when(petService).createPet(Mockito.any(Pet.class));

        // Realizar la solicitud POST
        ResultActions response = mockMvc.perform(
                post("/pets/")
                    .contentType("application/json")
                    .content(objectMapper.writeValueAsString(pet))
        );

        // Validar la respuesta
        response.andExpect(status().isCreated())
            .andExpect(content().contentType("application/json"))
            .andExpect(jsonPath("$.name").value(pet.getName()))
            .andExpect(jsonPath("$.breed").value(pet.getBreed()))
            .andExpect(jsonPath("$.birthDate").value(pet.getBirthDate().toString()))
            .andExpect(jsonPath("$.weight").value(pet.getWeight()))
            .andExpect(jsonPath("$.picture").value(pet.getPicture()))
            .andExpect(jsonPath("$.owner.name").value(alfredo.getName()));
    }
}