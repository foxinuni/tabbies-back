package dev.downloadablefox.tabbies.webserver.repositories;

import java.util.Collection;

import dev.downloadablefox.tabbies.webserver.entities.Pet;
import dev.downloadablefox.tabbies.webserver.entities.User;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository
public class MockDatabase implements UserRepository, PetRepository {
    private final Map<Long, User> userMap = new HashMap<>();

    private final Map<Long, Pet> pets = new HashMap<>();

    MockDatabase() {
        userMap.put(0L, new User(0L, 123456789, "Emilio", "emilio@gmail.com", 3206214141L));
        userMap.put(1L, new User(1L, 987654321, "Alfredo", "alfredo@gmail.com", 321623232L));
        userMap.put(2L, new User(2L, 345234214, "Miguel", "miguel@hotmail.com", 313231321L));

        pets.put(0L, new Pet(0L, "Emilio", "Esfinge", "26/02/2017", 4.2f, "https://i2.wp.com/enelveterinario.com/wp-content/uploads/2021/09/post_gato_esfinge.jpg?ssl=1"));
        pets.put(1L, new Pet(1L, "Alpacino", "Siames", "20/04/2019", 4.2f, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQzkmFUIlGYeJWHDlVQXoZEHmLTnz6BcbErmw&s"));
        pets.put(2L, new Pet(2L, "Mittens", "Tabby", "15/08/2018", 3.5f, "https://images.unsplash.com/photo-1592194996308-7b43878e84a6?ixlib=rb-1.2.1&auto=format&fit=crop&w=800&q=80"));
        pets.put(3L, new Pet(3L, "Whiskers", "Persian", "10/11/2016", 4.0f, "https://www.purina.co.uk/sites/default/files/2020-12/Understanding%20Your%20Cat%27s%20Body%20LanguageTEASER.jpg"));
        pets.put(4L, new Pet(4L, "Shadow", "Maine Coon", "05/01/2015", 5.0f, "https://images.unsplash.com/photo-1514888286974-6c03e2ca1dba?ixlib=rb-1.2.1&auto=format&fit=crop&w=800&q=80"));
        pets.put(5L, new Pet(5L, "Simba", "Bengal", "22/07/2020", 4.3f, "https://images.unsplash.com/photo-1533743983669-94fa5c4338ec?ixlib=rb-1.2.1&auto=format&fit=crop&w=800&q=80"));


        /* Adding pets to users */
        userMap.get(0L).setPets(List.of(0L, 1L));
        userMap.get(1L).setPets(List.of(2L, 3L, 4L));
        userMap.get(2L).setPets(List.of(5L));
    }

    /* Pets */
    @Override
    public Collection<Pet> findAllPets() {
        return pets.values();
    }

    @Override
    public Pet findPetById(Long id) {
        return pets.get(id);
    }

    @Override
    public void upsertPet(Pet pet) {
        pets.put(pet.getId(), pet);
    }

    @Override
    public void deletePetById(Long id) {
        pets.remove(id);
    }

    /* Users */
    @Override
    public Collection<User> findAllUsers() {
        return userMap.values();
    }

    @Override
    public User findUserById(Long id) {
        return userMap.get(id);
    }

    @Override
    public void upsertUser(User user) {
        userMap.put(user.getId(), user);
    }

    @Override
    public void deleteUserById(Long id) {
        userMap.remove(id);
    }
}
