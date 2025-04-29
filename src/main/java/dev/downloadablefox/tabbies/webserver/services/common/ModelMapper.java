package dev.downloadablefox.tabbies.webserver.services.common;

import org.springframework.stereotype.Service;

import dev.downloadablefox.tabbies.webserver.dtos.MedicineUpsert;
import dev.downloadablefox.tabbies.webserver.dtos.MedicineView;
import dev.downloadablefox.tabbies.webserver.dtos.PetUpsert;
import dev.downloadablefox.tabbies.webserver.dtos.PetView;
import dev.downloadablefox.tabbies.webserver.dtos.ProcedureUpsert;
import dev.downloadablefox.tabbies.webserver.dtos.ProcedureView;
import dev.downloadablefox.tabbies.webserver.dtos.UserUpsert;
import dev.downloadablefox.tabbies.webserver.dtos.UserView;
import dev.downloadablefox.tabbies.webserver.dtos.VeterinarianUpsert;
import dev.downloadablefox.tabbies.webserver.dtos.VeterinarianView;
import dev.downloadablefox.tabbies.webserver.entities.Medicine;
import dev.downloadablefox.tabbies.webserver.entities.Pet;
import dev.downloadablefox.tabbies.webserver.entities.Procedure;
import dev.downloadablefox.tabbies.webserver.entities.User;
import dev.downloadablefox.tabbies.webserver.entities.Veterinary;

@Service
public interface ModelMapper {
    // User
    User toUserEntity(UserUpsert userCreateDTO);
    UserView toUserDTO(User user);

    // Pet
    Pet toPetEntity(PetUpsert petCreateDTO);
    PetView toPetDTO(Pet pet);

    // Veterinary
    Veterinary toVeterinaryEntity(VeterinarianUpsert veterinaryCreateDTO);
    VeterinarianView toVeterinaryDTO(Veterinary veterinary);

    // Medicine
    Medicine toMedicineEntity(MedicineUpsert medicineCreateDTO);
    MedicineView toMedicineDTO(Medicine medicine);

    // Procedure
    Procedure toProcedureEntity(ProcedureUpsert procedureCreateDTO);
    ProcedureView toProcedureDTO(Procedure procedure);
}