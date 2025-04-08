package dev.downloadablefox.tabbies.webserver.dtos;

import java.time.LocalDate;

public class PetCreateDTO {
    private String name;
    private String breed;
    private float weight;
    private String picture;
    private LocalDate birthDate;
    private Long ownerId;
    private Boolean isDisabled;

    public PetCreateDTO() {}
    
    public PetCreateDTO(String name, String breed, float weight, String picture, LocalDate birthDate, Boolean isDisabled, Long ownerId) {
        this.name = name;
        this.breed = breed;
        this.weight = weight;
        this.picture = picture;
        this.birthDate = birthDate;
        this.isDisabled = isDisabled;
        this.ownerId = ownerId;
    }

    public String getName() {
        return name;
    }

    public String getBreed() {
        return breed;
    }

    public float getWeight() {
        return weight;
    }

    public String getPicture() {
        return picture;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public Boolean getIsDisabled() {
        return isDisabled;
    }

    public Long getOwnerId() {
        return ownerId;
    }
}
