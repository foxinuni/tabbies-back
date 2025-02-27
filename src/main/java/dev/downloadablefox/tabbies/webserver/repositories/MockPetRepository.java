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
        pets.put(2, new Pet(2, "Mittens", "Tabby", "15/08/2018", 3.5f, "https://images.unsplash.com/photo-1592194996308-7b43878e84a6?ixlib=rb-1.2.1&auto=format&fit=crop&w=800&q=80"));
        pets.put(3, new Pet(3, "Whiskers", "Persian", "10/11/2016", 4.0f, "https://www.purina.co.uk/sites/default/files/2020-12/Understanding%20Your%20Cat%27s%20Body%20LanguageTEASER.jpg"));
        pets.put(4, new Pet(4, "Shadow", "Maine Coon", "05/01/2015", 5.0f, "https://images.unsplash.com/photo-1514888286974-6c03e2ca1dba?ixlib=rb-1.2.1&auto=format&fit=crop&w=800&q=80"));
        pets.put(5, new Pet(5, "Simba", "Bengal", "22/07/2020", 4.3f, "https://images.unsplash.com/photo-1533743983669-94fa5c4338ec?ixlib=rb-1.2.1&auto=format&fit=crop&w=800&q=80"));
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
