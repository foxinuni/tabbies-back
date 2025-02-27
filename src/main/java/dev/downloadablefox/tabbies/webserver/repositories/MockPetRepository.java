package dev.downloadablefox.tabbies.webserver.repositories;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import dev.downloadablefox.tabbies.webserver.entities.Pet;

@Repository
public class MockPetRepository implements PetRepository {
    private final Map<Integer, Pet> pets = new HashMap<>();

    MockPetRepository() {
        pets.put(0, new Pet(0, "Emilio", "Esfinge", "26/02/2017", 4.2f, "https://i2.wp.com/enelveterinario.com/wp-content/uploads/2021/09/post_gato_esfinge.jpg?ssl=1"));
        pets.put(1, new Pet(1, "Alpacino", "Siames", "20/04/2019", 4.2f, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQzkmFUIlGYeJWHDlVQXoZEHmLTnz6BcbErmw&s"));
    }

    @Override
    public Collection<Pet> findAll() {
        return pets.values();
    }

    @Override
    public Pet findById(Integer id) {
        return pets.get(id);
    }
    
}
