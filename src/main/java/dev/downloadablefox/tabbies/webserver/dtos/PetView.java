package dev.downloadablefox.tabbies.webserver.dtos;

import java.time.LocalDate;

public class PetView {
    private Long id;
    private String name;
    private String breed;
    private float weight;
    private String picture;
    private LocalDate birthDate;
    private Long ownerId;
    private boolean disabled;

    public PetView(Long id, String name, String breed, float weight, String picture, LocalDate birthDate, Long ownerId, Boolean isDisabled) {
        this.id = id;
        this.name = name;
        this.breed = breed;
        this.weight = weight;
        this.picture = picture;
        this.birthDate = birthDate;
        this.ownerId = ownerId;
        this.disabled = isDisabled;
    }

    public Long getId() {
        return id;
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

    public Long getOwnerId() {
        return ownerId;
    }

    public boolean getDisabled() {
        return disabled;
    }

    public String toString() {
        return "PetView[id=" + id + ", name=" + name + ", breed=" + breed + ", weight=" + weight + ", picture=" + picture + ", birthDate=" + birthDate + ", ownerId=" + ownerId + ", isDisabled=" + disabled + "]";
    }
}
