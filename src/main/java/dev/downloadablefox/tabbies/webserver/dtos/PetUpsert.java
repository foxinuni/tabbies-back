package dev.downloadablefox.tabbies.webserver.dtos;

import java.time.LocalDate;

public class PetUpsert {
    private String name;
    private String breed;
    private float weight;
    private String picture;
    private LocalDate birthDate;
    private Long ownerId;
    private Boolean disabled;

    public PetUpsert() {}
    
    public PetUpsert(String name, String breed, float weight, String picture, LocalDate birthDate, Boolean isDisabled, Long ownerId) {
        this.name = name;
        this.breed = breed;
        this.weight = weight;
        this.picture = picture;
        this.birthDate = birthDate;
        this.disabled = isDisabled;
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

    public Boolean getDisabled() {
        return disabled;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public String toString() {
        return "PetUpsert[name=" + name + ", breed=" + breed + ", weight=" + weight + ", picture=" + picture + ", birthDate=" + birthDate + ", isDisabled=" + disabled + ", ownerId=" + ownerId + "]";
    }
}
