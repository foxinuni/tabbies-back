package dev.downloadablefox.tabbies.webserver.services.common;

import org.springframework.beans.factory.annotation.Autowired;
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
import dev.downloadablefox.tabbies.webserver.entities.Role;
import dev.downloadablefox.tabbies.webserver.entities.RoleType;
import dev.downloadablefox.tabbies.webserver.entities.User;
import dev.downloadablefox.tabbies.webserver.entities.Veterinary;
import dev.downloadablefox.tabbies.webserver.services.medicine.MedicineService;
import dev.downloadablefox.tabbies.webserver.services.pets.PetService;
import dev.downloadablefox.tabbies.webserver.services.roles.RoleService;
import dev.downloadablefox.tabbies.webserver.services.user.UserService;
import dev.downloadablefox.tabbies.webserver.services.veterinary.VeterinarianService;

@Service
public class ModelMapperImpl implements ModelMapper {
    @Autowired
    private UserService userService;

    @Autowired
    private PetService petService;

    @Autowired
    private MedicineService medicineService;

    @Autowired
    private VeterinarianService veterinarianService;

    @Autowired
    private RoleService roleService;

    @Override
    public User toUserEntity(UserUpsert dto) {
        final Role role = roleService.getRoleByType(RoleType.USER);
        return new User(dto.getEmail(), dto.getPassword(), role, dto.getDocument(), dto.getName(), dto.getNumber());
    }

    @Override
    public UserView toUserDTO(User user) {
        return new UserView(user.getId(), user.getDocument(), user.getName(), user.getEmail(), user.getNumber());
    }

    @Override
    public Pet toPetEntity(PetUpsert dto) {
        final User user = userService.getUserById(dto.getOwnerId());
        return new Pet(dto.getName(), dto.getBreed(), dto.getBirthDate(), dto.getWeight(), dto.getPicture(), user, dto.getDisabled());
    }

    @Override
    public PetView toPetDTO(Pet pet) {
        return new PetView(pet.getId(), pet.getName(), pet.getBreed(), pet.getWeight(), pet.getPicture(), pet.getBirthDate(), pet.getOwner().getId(), pet.getIsDisabled());
    }

    @Override
    public Veterinary toVeterinaryEntity(VeterinarianUpsert dto) {
        final Role role = roleService.getRoleByType(RoleType.VETERINARY);
        return new Veterinary(dto.getEmail(), dto.getPassword(), role, dto.getSpeciality(), dto.getPicture(), dto.getDocument(), dto.getName(), dto.getNumber());
    }

    @Override
    public VeterinarianView toVeterinaryDTO(Veterinary veterinary) {
        return new VeterinarianView(veterinary.getId(), veterinary.getEmail(), veterinary.getRole().getName(), veterinary.getName(), veterinary.getDocument(), veterinary.getNumber(), veterinary.getSpeciality(), veterinary.getPicture());
    }

    @Override
    public Medicine toMedicineEntity(MedicineUpsert medicine) {
        return new Medicine(medicine.getName(), medicine.getBuyPrice(), medicine.getSellPrice(), medicine.getStock(), medicine.getStock());
    }

    @Override
    public MedicineView toMedicineDTO(Medicine medicine) {
        return new MedicineView(medicine.getId(), medicine.getName(), medicine.getBuyPrice(), medicine.getSellPrice(), medicine.getStock(), medicine.getSold());
    }

    @Override
    public Procedure toProcedureEntity(ProcedureUpsert procedureCreateDTO) {
        final Pet pet = petService.getPetById(procedureCreateDTO.getPetId());
        final Veterinary veterinary = veterinarianService.getVeterinarianById(procedureCreateDTO.getVeterinaryId());
        final Medicine medicine = medicineService.getMedicineById(procedureCreateDTO.getMedicineId());
        return new Procedure(procedureCreateDTO.getQuantity(), procedureCreateDTO.getNotes(), pet, medicine, veterinary);
    }
    
    @Override
    public ProcedureView toProcedureDTO(Procedure procedure) {
        final Medicine medicine = procedure.getMedicine();
        return new ProcedureView(procedure.getId(), procedure.getQuantity(), procedure.getNotes(), procedure.getPet().getId(), (medicine == null) ? null : medicine.getId(), procedure.getVeterinary().getId());
    }
}
