package dev.downloadablefox.tabbies.webserver.database;

import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

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
import dev.downloadablefox.tabbies.webserver.services.medicine.MedicineExcelService;
import dev.downloadablefox.tabbies.webserver.services.roles.RoleService;
import jakarta.transaction.Transactional;

@Component
@Transactional
@Profile("test")
public class DatabaseInitTest implements ApplicationRunner {
    Random random = new Random();

    @Autowired
    UserRepository userRepository;

    @Autowired
    PetRepository petRepository;

    @Autowired
    VeterinaryRepository veterinaryRepository;

    @Autowired
    ProcedureRepository procedureRepository;

    @Autowired
    MedicineRepository medicineRepository;

    @Autowired
    MedicineExcelService medicineExcelService;

    @Autowired
    RoleService roleService;

    private void generateUsers(List<User> users) {
        final Role userRole = roleService.getRoleByType(RoleType.USER);

        final String[] nombres = {
            "Augusto", "Carlos", "Lucía", "Marta", "Andrés", "Sofía", "Javier", "Ana", "Roberto", "Elena",
            "Fernando", "Gabriela", "Diego", "Patricia", "Luis", "Camila", "Daniel", "Isabel", "Manuel", "Valeria",
            "Ricardo", "Mariana", "Alejandro", "Paula", "Hugo", "Natalia", "Mateo", "Carla", "Samuel", "Victoria",
            "Raúl", "Clara", "Adrián", "Beatriz", "Ignacio", "Julia", "Sebastián", "Lorena", "Gonzalo", "Andrea",
            "Tomás", "Eva", "Alberto", "Pilar", "Enrique", "Cecilia", "Óscar", "Rocío", "Marcos", "Esther"
        };

        for (int i = 0; i < 50; i++) {
            String nombre = nombres[i % nombres.length];
            String hash = nombres[i % nombres.length].toLowerCase();
            int documento = 100000000 + random.nextInt(900000000);
            long numero = 3000000000L + random.nextInt(1000000000);

            String prefix = nombre.toLowerCase()
                .replaceAll("[áàäâ]", "a")
                .replaceAll("[éèëê]", "e")
                .replaceAll("[íìïî]", "i")
                .replaceAll("[óòöô]", "o")
                .replaceAll("[úùüû]", "u")
                .replaceAll("[ñ]", "n")
                .replaceAll("[^a-z]", "") // Remove non-alphabetic characters
                .replace(" ", "-"); // Replace spaces with hyphens

            String email = String.format("%s%d@email.com", prefix, i);

            final User user = new User(email, hash, userRole, documento, nombre, numero);
            users.add(userRepository.save(user));
        }
    }

    private void generatePets(List<Pet> pets, List<User> users) {
        final String[] petNames = {"Bella", "Charlie", "Max", "Luna", "Rocky", "Milo", "Lucy", "Daisy", "Bailey", "Oliver",
                             "Chloe", "Buddy", "Lola", "Jack", "Sophie", "Zoe", "Toby", "Maggie", "Finn", "Nala",
                             "Simba", "Coco", "Ginger", "Oscar", "Ruby", "Jasper", "Sadie", "Molly", "Riley", "Sasha",
                             "Lily", "Cooper", "Lexi", "Ava", "Ben", "Emma", "Finn", "Gracie", "Hunter", "Isabella",
                             "Leo", "Mia", "Max", "Nora", "Oliver", "Piper", "Remy", "Ruby", "Sage", "Toby", "Willow"};

        final String[] petBreeds = {"Esfinge", "Siames", "Tabby", "Persian", "Maine Coon", "Bengal", "Siberian", "Ragdoll", "Sphynx", "British Shorthair"};

        final String[] petImages = {
            "https://i2.wp.com/enelveterinario.com/wp-content/uploads/2021/09/post_gato_esfinge.jpg?ssl=1",
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQzkmFUIlGYeJWHDlVQXoZEHmLTnz6BcbErmw&s",
            "https://images.unsplash.com/photo-1592194996308-7b43878e84a6?ixlib=rb-1.2.1&auto=format&fit=crop&w=800&q=80",
            "https://www.purina.co.uk/sites/default/files/2020-12/Understanding%20Your%20Cat%27s%20Body%20LanguageTEASER.jpg",
            "https://images.unsplash.com/photo-1514888286974-6c03e2ca1dba?ixlib=rb-1.2.1&auto=format&fit=crop&w=800&q=80",
            "https://images.unsplash.com/photo-1533743983669-94fa5c4338ec?ixlib=rb-1.2.1&auto=format&fit=crop&w=800&q=80"
        };

        for (int i = 0; i < 100; i++) {
            String name = petNames[i % petNames.length];
            String breed = petBreeds[i % petBreeds.length];
            String picture = petImages[random.nextInt(petImages.length)];
            User owner = users.get(i % users.size());
            LocalDate birthDate = LocalDate.of(2015 + random.nextInt(10), 1 + random.nextInt(12), 1 + random.nextInt(28));
            float weight = (float) (3.0 + Math.round((random.nextFloat() * 2.0 + NumberFormat.getInstance().getMaximumFractionDigits()) * 10.0) / 10.0);

            final Pet pet = new Pet(name, breed, birthDate, weight, picture, owner, false);
            pets.add(petRepository.save(pet));
        }
    }

    private void generateVets(List<Veterinary> veterinarians) {
        final Role veterinaryRole = roleService.getRoleByType(RoleType.VETERINARY);

        final String[] veterinaryNames = {
            "Dr. Juan Pérez", "Dra. María López", "Dr. Carlos Martínez", "Dra. Laura Gómez",
            "Dr. Andrés Herrera", "Dra. Camila Torres", "Dr. Fernando Ríos", "Dra. Valentina Suárez",
            "Dr. Sebastián Gómez", "Dra. Carolina Mendoza"
        };

        final String[] specialties = {"Dermatología", "Cardiología", "Neurología", "Ortopedia"};

        final String[] veterinaryImages = {
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

            Long number = 310000000L + random.nextInt(90000000);
            Integer document = 200000000 + random.nextInt(800000000);
            String email = name.toLowerCase()
                                .replaceAll("dr\\.\\s*|dra\\.\\s*", "") // Remove "Dr." or "Dra." prefix
                                .replaceAll("[áàäâ]", "a")
                                .replaceAll("[éèëê]", "e")
                                .replaceAll("[íìïî]", "i")
                                .replaceAll("[óòöô]", "o")
                                .replaceAll("[úùüû]", "u")
                                .replaceAll("[ñ]", "n")
                                .replaceAll("[^a-z ]", "") // Remove non-alphabetic characters
                                .replace(" ", "-") + "@tabbies.com";  // Email formatted as name-surname@tabbies.com

            final Veterinary veterinary = new Veterinary(email, "generado", veterinaryRole, specialty, picture, document, name, number);
            veterinarians.add(veterinaryRepository.save(veterinary));
        }
    }

    private void generateProcedures(List<Procedure> procedures, List<Pet> pets, List<Veterinary> veterinarians, List<Medicine> medicines) {
        final String[] procedureNotes = {
            "Consulta general", "Vacunación", "Desparacitación", "Chequeo dental",
            "Radiografía", "Ultrasonido", "Cirugía menor", "Consulta de emergencia",
            "Control de peso", "Consulta dermatológica"
        };
        
        for(int i = 0; i < 10; i++) {
            String note = procedureNotes[i % procedureNotes.length];
            int quantity = 1 + random.nextInt(3);

            Medicine medicine = null;
            Pet pet = pets.get(i % pets.size());
            Veterinary veterinary = veterinarians.get((i + 1) % veterinarians.size());

            final Procedure procedure = new Procedure(quantity, note, pet, medicine, veterinary);
            procedureRepository.save(procedure);
        };
    }

    private void generateMedicines(List<Medicine> medicines) {
        try {
            medicines = medicineExcelService.loadMedicinesFromResource();
            medicineRepository.saveAll(medicines);
            System.out.println("Medicamentos cargados exitosamente: " + medicines.size());
        } catch (Exception e) {
            System.err.println("Error cargando medicamentos: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        final List<User> users = new ArrayList<>();
        final List<Pet> pets = new ArrayList<>();
        final List<Veterinary> veterinarians = new ArrayList<>();
        final List<Procedure> procedures = new ArrayList<>();
        final List<Medicine> medicines = new ArrayList<>();

        // Generate basic roles
        roleService.createRole(RoleType.USER.getRole());
        roleService.createRole(RoleType.VETERINARY.getRole());
        roleService.createRole(RoleType.ADMIN.getRole());

        final Role userRole = roleService.getRoleByType(RoleType.USER);
        final Role adminRole = roleService.getRoleByType(RoleType.ADMIN);
        final Role veterinaryRole = roleService.getRoleByType(RoleType.VETERINARY);
        

        // Base users for testing
        User emilio = userRepository.save(new User("emilio@gmail.com", "emilio", userRole, 123456789, "Emilio", 3206214141L));
        User alfredo = userRepository.save(new User("alfredo@gmail.com", "alfredo", userRole, 987654321, "Alfredo", 321623232L));
        User miguel = userRepository.save(new User("miguel@hotmail.com", "miguel", userRole, 345234214, "Miguel", 313231321L));

        // Base pets for testing
        petRepository.save(new Pet("Emilio", "Esfinge", LocalDate.of(2017, 2, 26), 4.2f, "https://i2.wp.com/enelveterinario.com/wp-content/uploads/2021/09/post_gato_esfinge.jpg?ssl=1", emilio, false));
        petRepository.save(new Pet("Alpacino", "Siames", LocalDate.of(2019, 4, 20), 4.2f, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQzkmFUIlGYeJWHDlVQXoZEHmLTnz6BcbErmw&s", emilio, false));
        petRepository.save(new Pet("Mittens", "Tabby", LocalDate.of(2018, 8, 15), 3.5f, "https://images.unsplash.com/photo-1592194996308-7b43878e84a6?ixlib=rb-1.2.1&auto=format&fit=crop&w=800&q=80", alfredo, false));
        petRepository.save(new Pet("Whiskers", "Persian", LocalDate.of(2016, 11, 10), 4.0f, "https://www.purina.co.uk/sites/default/files/2020-12/Understanding%20Your%20Cat%27s%20Body%20LanguageTEASER.jpg", alfredo, false));
        petRepository.save(new Pet("Shadow", "Maine Coon", LocalDate.of(2015, 1, 5), 5.0f, "https://images.unsplash.com/photo-1514888286974-6c03e2ca1dba?ixlib=rb-1.2.1&auto=format&fit=crop&w=800&q=80", alfredo, false));
        petRepository.save(new Pet("Simba", "Bengal", LocalDate.of(2020, 7, 22), 4.3f, "https://images.unsplash.com/photo-1533743983669-94fa5c4338ec?ixlib=rb-1.2.1&auto=format&fit=crop&w=800&q=80", miguel, false));
        
        // Base veterinarians for testing
        veterinarians.add(veterinaryRepository.save(new Veterinary("admin@tabbies.com", "admin", adminRole, "Administrador", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQMPqRTUTBCSQUjVitzJEigfGchESQgRsk4zQ&s", 123456789, "Admin", 3206214141L)));
        veterinarians.add(veterinaryRepository.save(new Veterinary("vet@tabbies.com", "vet", veterinaryRole, "Veterinario", "https://www.promedco.com/images/NOTICIAS_2020/reducir-estres-de-mascotas-1.jpg", 987654321, "Vet", 321623232L)));
        
        generateUsers(users); // Generate users with random data
        generatePets(pets, users); // Generate pets with random data
        generateVets(veterinarians); // Generate veterinarians with random data
        generateMedicines(medicines); // Generate medicines from Excel file
        generateProcedures(procedures, pets, veterinarians, medicines); // Generate procedures with random data

        System.out.println("Base de datos inicializada con éxito.");
    }
}