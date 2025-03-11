package dev.downloadablefox.tabbies.webserver.entities;

import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import dev.downloadablefox.tabbies.webserver.repositories.PetRepository;
import dev.downloadablefox.tabbies.webserver.repositories.UserRepository;
import jakarta.transaction.Transactional;

@Component
@Transactional
public class DatabaseInit implements ApplicationRunner {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PetRepository petRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        User emilio = userRepository.save(new User(123456789, "Emilio", "emilio@gmail.com", "emilio", 3206214141L));
        User alfredo = userRepository.save(new User(987654321, "Alfredo", "alfredo@gmail.com", "alfredo", 321623232L));
        User miguel = userRepository.save(new User(345234214, "Miguel", "miguel@hotmail.com", "miguel", 313231321L));

        petRepository.save(new Pet("Emilio", "Esfinge", LocalDate.of(2017, 2, 26), 4.2f, "https://i2.wp.com/enelveterinario.com/wp-content/uploads/2021/09/post_gato_esfinge.jpg?ssl=1", emilio));
        petRepository.save(new Pet("Alpacino", "Siames", LocalDate.of(2019, 4, 20), 4.2f, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQzkmFUIlGYeJWHDlVQXoZEHmLTnz6BcbErmw&s", emilio));
        petRepository.save(new Pet("Mittens", "Tabby", LocalDate.of(2018, 8, 15), 3.5f, "https://images.unsplash.com/photo-1592194996308-7b43878e84a6?ixlib=rb-1.2.1&auto=format&fit=crop&w=800&q=80", alfredo));
        petRepository.save(new Pet("Whiskers", "Persian", LocalDate.of(2016, 11, 10), 4.0f, "https://www.purina.co.uk/sites/default/files/2020-12/Understanding%20Your%20Cat%27s%20Body%20LanguageTEASER.jpg", alfredo));
        petRepository.save(new Pet("Shadow", "Maine Coon", LocalDate.of(2015, 1, 5), 5.0f, "https://images.unsplash.com/photo-1514888286974-6c03e2ca1dba?ixlib=rb-1.2.1&auto=format&fit=crop&w=800&q=80", alfredo));
        petRepository.save(new Pet("Simba", "Bengal", LocalDate.of(2020, 7, 22), 4.3f, "https://images.unsplash.com/photo-1533743983669-94fa5c4338ec?ixlib=rb-1.2.1&auto=format&fit=crop&w=800&q=80", miguel));
    }
}