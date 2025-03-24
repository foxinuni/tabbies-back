package dev.downloadablefox.tabbies.webserver.entities;

import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import dev.downloadablefox.tabbies.webserver.repositories.PetRepository;
import dev.downloadablefox.tabbies.webserver.repositories.UserRepository;
import dev.downloadablefox.tabbies.webserver.repositories.VeterinaryRepository;
import jakarta.transaction.Transactional;

@Component
@Transactional
public class DatabaseInit implements ApplicationRunner {
    Random random = new Random();

    @Autowired
    UserRepository userRepository;

    @Autowired
    PetRepository petRepository;

    @Autowired
    VeterinaryRepository veterinaryRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        List<User> users = new ArrayList<>();

        User emilio = userRepository.save(new User(123456789, "Emilio", "emilio@gmail.com", "emilio", 3206214141L));
        User alfredo = userRepository.save(new User(987654321, "Alfredo", "alfredo@gmail.com", "alfredo", 321623232L));
        User miguel = userRepository.save(new User(345234214, "Miguel", "miguel@hotmail.com", "miguel", 313231321L));

        String[] nombres = {"Augusto", "Carlos", "Lucía", "Marta", "Andrés", "Sofía", "Javier", "Ana", "Roberto", "Elena",
                            "Fernando", "Gabriela", "Diego", "Patricia", "Luis", "Camila", "Daniel", "Isabel", "Manuel", "Valeria",
                            "Ricardo", "Mariana", "Alejandro", "Paula", "Hugo", "Natalia", "Mateo", "Carla", "Samuel", "Victoria",
                            "Raúl", "Clara", "Adrián", "Beatriz", "Ignacio", "Julia", "Sebastián", "Lorena", "Gonzalo", "Andrea",
                            "Tomás", "Eva", "Alberto", "Pilar", "Enrique", "Cecilia", "Óscar", "Rocío", "Marcos", "Esther"};
        
        for (int i = 0; i < 50; i++) {
            int documento = 100000000 + random.nextInt(900000000);
            String nombre = nombres[i % nombres.length];
            String email = nombre.toLowerCase() + "@example.com";
            String hash = nombres[i % nombres.length].toLowerCase();
            long numero = 3000000000L + random.nextInt(1000000000);
            
            users.add(userRepository.save(new User(documento, nombre, email, hash, numero)));
        }

        petRepository.save(new Pet("Emilio", "Esfinge", LocalDate.of(2017, 2, 26), 4.2f, "https://i2.wp.com/enelveterinario.com/wp-content/uploads/2021/09/post_gato_esfinge.jpg?ssl=1", emilio, false));
        petRepository.save(new Pet("Alpacino", "Siames", LocalDate.of(2019, 4, 20), 4.2f, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQzkmFUIlGYeJWHDlVQXoZEHmLTnz6BcbErmw&s", emilio, false));
        petRepository.save(new Pet("Mittens", "Tabby", LocalDate.of(2018, 8, 15), 3.5f, "https://images.unsplash.com/photo-1592194996308-7b43878e84a6?ixlib=rb-1.2.1&auto=format&fit=crop&w=800&q=80", alfredo, false));
        petRepository.save(new Pet("Whiskers", "Persian", LocalDate.of(2016, 11, 10), 4.0f, "https://www.purina.co.uk/sites/default/files/2020-12/Understanding%20Your%20Cat%27s%20Body%20LanguageTEASER.jpg", alfredo, false));
        petRepository.save(new Pet("Shadow", "Maine Coon", LocalDate.of(2015, 1, 5), 5.0f, "https://images.unsplash.com/photo-1514888286974-6c03e2ca1dba?ixlib=rb-1.2.1&auto=format&fit=crop&w=800&q=80", alfredo, false));
        petRepository.save(new Pet("Simba", "Bengal", LocalDate.of(2020, 7, 22), 4.3f, "https://images.unsplash.com/photo-1533743983669-94fa5c4338ec?ixlib=rb-1.2.1&auto=format&fit=crop&w=800&q=80", miguel, false));
        
        String[] petNames = {"Bella", "Charlie", "Max", "Luna", "Rocky", "Milo", "Lucy", "Daisy", "Bailey", "Oliver",
                             "Chloe", "Buddy", "Lola", "Jack", "Sophie", "Zoe", "Toby", "Maggie", "Finn", "Nala",
                             "Simba", "Coco", "Ginger", "Oscar", "Ruby", "Jasper", "Sadie", "Molly", "Riley", "Sasha",
                             "Lily", "Cooper", "Lexi", "Ava", "Ben", "Emma", "Finn", "Gracie", "Hunter", "Isabella",
                             "Leo", "Mia", "Max", "Nora", "Oliver", "Piper", "Remy", "Ruby", "Sage", "Toby", "Willow"};

        String[] petBreeds = {"Esfinge", "Siames", "Tabby", "Persian", "Maine Coon", "Bengal", "Siberian", "Ragdoll", "Sphynx", "British Shorthair"};
        String[] petImages = {
            "https://i2.wp.com/enelveterinario.com/wp-content/uploads/2021/09/post_gato_esfinge.jpg?ssl=1",
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQzkmFUIlGYeJWHDlVQXoZEHmLTnz6BcbErmw&s",
            "https://images.unsplash.com/photo-1592194996308-7b43878e84a6?ixlib=rb-1.2.1&auto=format&fit=crop&w=800&q=80",
            "https://www.purina.co.uk/sites/default/files/2020-12/Understanding%20Your%20Cat%27s%20Body%20LanguageTEASER.jpg",
            "https://images.unsplash.com/photo-1514888286974-6c03e2ca1dba?ixlib=rb-1.2.1&auto=format&fit=crop&w=800&q=80",
            "https://images.unsplash.com/photo-1533743983669-94fa5c4338ec?ixlib=rb-1.2.1&auto=format&fit=crop&w=800&q=80"
        };

        for (int i = 1; i <= 100; i++) {
            String name = petNames[i % petNames.length];
            String breed = petBreeds[i % petBreeds.length];
            LocalDate birthDate = LocalDate.of(2015 + random.nextInt(10), 1 + random.nextInt(12), 1 + random.nextInt(28));
            float weight = (float) (3.0 + Math.round((random.nextFloat() * 2.0 + NumberFormat.getInstance().getMaximumFractionDigits()) * 10.0) / 10.0);
            String picture = petImages[random.nextInt(petImages.length)];
            User owner = users.get(random.nextInt(users.size()));

            petRepository.save(new Pet(name, breed, birthDate, weight, picture, owner, false));
        }


        String[] veterinaryNames = {
            "Dr. Juan Pérez", "Dra. María López", "Dr. Carlos Martínez", "Dra. Laura Gómez",
            "Dr. Andrés Herrera", "Dra. Camila Torres", "Dr. Fernando Ríos", "Dra. Valentina Suárez",
            "Dr. Sebastián Gómez", "Dra. Carolina Mendoza"
        };
        String[] specialties = {"Dermatología", "Cardiología", "Neurología", "Ortopedia"};
        String[] veterinaryImages = {
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQMPqRTUTBCSQUjVitzJEigfGchESQgRsk4zQ&s",
            "https://www.promedco.com/images/NOTICIAS_2020/reducir-estres-de-mascotas-1.jpg",
            "https://universidadeuropea.com/resources/media/images/medicina-veterinaria-1200x630.original.jpg",
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSUeEspVdBXUg8ZzynfCSeBtkqL69d-utAnP7VfdkojWQWLmucxfI1SVqQpc7sQAvfwwsY&usqp=CAU",
            "https://nupec.com/wp-content/uploads/2022/09/vet-appointment-2021-09-24-03-25-35-utc-min.jpg",
            "https://www.elespectador.com/resizer/v2/I7JAUZSYVVGIBLOW74GBKIQARY.jpg?auth=77942d54ba8710566cfa4aa4830e38150bc562d87031c13b86cce929b8358b95&width=920&height=613&smart=true&quality=60"
            
        };

    
        for (int i = 0; i < 10; i++) {
            String name = veterinaryNames[i % veterinaryNames.length];
            String specialty = specialties[i % specialties.length];
            String picture = veterinaryImages[i % veterinaryImages.length];
            String role = "Veterinary";
        
            Integer document = 200000000 + random.nextInt(800000000);
            String email = name.toLowerCase().replace(" ", ".") + document + "@Tabbies.com";  // Email único
            Long number = 310000000L + random.nextInt(90000000);  // Rango adecuado para un número telefónico
        
            veterinaryRepository.save(new Veterinary(role, specialty, picture, document, name, email, number));
        }
        

    }
}