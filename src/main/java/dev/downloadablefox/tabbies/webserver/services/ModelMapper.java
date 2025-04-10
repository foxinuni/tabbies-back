package dev.downloadablefox.tabbies.webserver.services;

import org.springframework.stereotype.Service;

import dev.downloadablefox.tabbies.webserver.dtos.PetUpsert;
import dev.downloadablefox.tabbies.webserver.dtos.PetView;
import dev.downloadablefox.tabbies.webserver.dtos.UserUpsert;
import dev.downloadablefox.tabbies.webserver.dtos.UserView;
import dev.downloadablefox.tabbies.webserver.entities.Pet;
import dev.downloadablefox.tabbies.webserver.entities.User;

@Service
public interface ModelMapper {
    User toUserEntity(UserUpsert userCreateDTO);
    UserView toUserDTO(User user);

    Pet toPetEntity(PetUpsert petCreateDTO);
    PetView toPetDTO(Pet pet);
}