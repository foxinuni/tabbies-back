package dev.downloadablefox.tabbies.webserver.services;

import org.springframework.stereotype.Service;

import dev.downloadablefox.tabbies.webserver.dtos.PetUpsert;
import dev.downloadablefox.tabbies.webserver.dtos.PetView;
import dev.downloadablefox.tabbies.webserver.dtos.UserUpsert;
import dev.downloadablefox.tabbies.webserver.dtos.UserView;
import dev.downloadablefox.tabbies.webserver.dtos.VeterinarianUpsert;
import dev.downloadablefox.tabbies.webserver.dtos.VeterinarianView;
import dev.downloadablefox.tabbies.webserver.entities.Pet;
import dev.downloadablefox.tabbies.webserver.entities.User;
import dev.downloadablefox.tabbies.webserver.entities.Veterinary;

@Service
public interface ModelMapper {
    User toUserEntity(UserUpsert userCreateDTO);
    UserView toUserDTO(User user);

    Pet toPetEntity(PetUpsert petCreateDTO);
    PetView toPetDTO(Pet pet);

    Veterinary toVeterinaryEntity(VeterinarianUpsert veterinaryCreateDTO);
    VeterinarianView toVeterinaryDTO(Veterinary veterinary);
}