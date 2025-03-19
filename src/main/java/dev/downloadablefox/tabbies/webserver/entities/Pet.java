package dev.downloadablefox.tabbies.webserver.entities;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Pet {
    private String name;
    private String breed;
    private float weight;
    private String picture;
    private LocalDate birthDate;
    private Boolean isDisabled;
    
    @ManyToOne
    @JoinColumn(name = "owner_id", nullable = false)
    private User owner;

    @ManyToOne(optional = true)
    @JoinColumn(name="idDesease")
    private Disease disease;

    @Id
    @GeneratedValue
    private Long id;


    public Pet(String name, String breed, LocalDate birthDate, float weight, String picture, User owner, Boolean isDisabled) {
        this.name = name;
        this.breed = breed;
        this.birthDate = birthDate;
        this.weight = weight;
        this.picture = picture;
        this.owner = owner;
        this.isDisabled = isDisabled;
        this.disease = null;
    }

    public Pet(String name, String breed, LocalDate birthDate, float weight, String picture, User owner, Boolean isDisabled, Disease disease) {
        this.name = name;
        this.breed = breed;
        this.birthDate = birthDate;
        this.weight = weight;
        this.picture = picture;
        this.owner = owner;
        this.isDisabled = isDisabled;
        this.disease = disease;
    }

    public Pet() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    } 
    
    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public Boolean getIsDisabled() {
        return isDisabled;
    }

    public void setIsDisabled(Boolean isDisabled) {
        this.isDisabled = isDisabled;
    }

    public Disease getDisease() {
        return disease;
    }
    public void setDisease(Disease disease) {
        this.disease = disease;
    }
}
