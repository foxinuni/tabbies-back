package dev.downloadablefox.tabbies.webserver.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.downloadablefox.tabbies.webserver.dtos.PetCreateDTO;
import dev.downloadablefox.tabbies.webserver.dtos.PetGetDTO;
import dev.downloadablefox.tabbies.webserver.dtos.UserCreateDTO;
import dev.downloadablefox.tabbies.webserver.dtos.UserGetDTO;
import dev.downloadablefox.tabbies.webserver.entities.Pet;
import dev.downloadablefox.tabbies.webserver.entities.User;

@Service
public class ModelMapperImpl implements ModelMapper {
    @Autowired
    private UserService userService;

    @Override
    public User toUserEntity(UserCreateDTO userCreateDTO) {
        return new User(userCreateDTO.getDocument(), userCreateDTO.getName(), userCreateDTO.getEmail(), userCreateDTO.getPassword(), userCreateDTO.getNumber());
    }

    @Override
    public UserGetDTO toUserDTO(User user) {
        return new UserGetDTO(user.getId(), user.getDocument(), user.getName(), user.getEmail(), user.getNumber());
    }

    @Override
    public Pet toPetEntity(PetCreateDTO petCreateDTO) {
        User user = userService.getUserById(petCreateDTO.getOwnerId());

        return new Pet(petCreateDTO.getName(), petCreateDTO.getBreed(), petCreateDTO.getBirthDate(), petCreateDTO.getWeight(), petCreateDTO.getPicture(), user, petCreateDTO.getIsDisabled());
    }

    @Override
    public PetGetDTO toPetDTO(Pet pet) {
        return new PetGetDTO(pet.getId(), pet.getName(), pet.getBreed(), pet.getWeight(), pet.getPicture(), pet.getBirthDate(), pet.getOwner().getId(), pet.getIsDisabled());
    }
    
}
