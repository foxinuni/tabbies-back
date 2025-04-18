package dev.downloadablefox.tabbies.webserver.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.downloadablefox.tabbies.webserver.dtos.PetUpsert;
import dev.downloadablefox.tabbies.webserver.dtos.PetView;
import dev.downloadablefox.tabbies.webserver.dtos.UserUpsert;
import dev.downloadablefox.tabbies.webserver.dtos.UserView;
import dev.downloadablefox.tabbies.webserver.entities.Pet;
import dev.downloadablefox.tabbies.webserver.entities.User;

@Service
public class ModelMapperImpl implements ModelMapper {
    @Autowired
    private UserService userService;

    @Override
    public User toUserEntity(UserUpsert dto) {
        return new User(dto.getDocument(), dto.getName(), dto.getEmail(), dto.getPassword(), dto.getNumber());
    }

    @Override
    public UserView toUserDTO(User user) {
        return new UserView(user.getId(), user.getDocument(), user.getName(), user.getEmail(), user.getNumber());
    }

    @Override
    public Pet toPetEntity(PetUpsert dto) {
        User user = userService.getUserById(dto.getOwnerId());
        return new Pet(dto.getName(), dto.getBreed(), dto.getBirthDate(), dto.getWeight(), dto.getPicture(), user, dto.getDisabled());
    }

    @Override
    public PetView toPetDTO(Pet pet) {
        return new PetView(pet.getId(), pet.getName(), pet.getBreed(), pet.getWeight(), pet.getPicture(), pet.getBirthDate(), pet.getOwner().getId(), pet.getIsDisabled());
    }
    
}
