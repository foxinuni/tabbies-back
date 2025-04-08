package dev.downloadablefox.tabbies.webserver.services;

import org.springframework.stereotype.Service;

import dev.downloadablefox.tabbies.webserver.dtos.PetCreateDTO;
import dev.downloadablefox.tabbies.webserver.dtos.PetGetDTO;
import dev.downloadablefox.tabbies.webserver.dtos.UserCreateDTO;
import dev.downloadablefox.tabbies.webserver.dtos.UserGetDTO;
import dev.downloadablefox.tabbies.webserver.entities.Pet;
import dev.downloadablefox.tabbies.webserver.entities.User;

@Service
public interface ModelMapper {
    User toUserEntity(UserCreateDTO userCreateDTO);
    UserGetDTO toUserDTO(User user);

    Pet toPetEntity(PetCreateDTO petCreateDTO);
    PetGetDTO toPetDTO(Pet pet);
}