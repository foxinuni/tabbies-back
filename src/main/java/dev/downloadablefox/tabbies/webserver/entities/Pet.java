package dev.downloadablefox.tabbies.webserver.entities;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Pet {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String breed;
    
    @Column(nullable = false)
    private float weight;

    @Column(nullable = false)
    private String picture;

    @Column(nullable = false)
    private LocalDate birthDate;

    @Column(nullable = false)
    private Boolean isDisabled;
    
    @ManyToOne
    @JoinColumn(name = "owner_id", nullable = false)
    private User owner;

    @ManyToOne(optional = true)
    @JoinColumn(name="desease_id", nullable = true)
    private Disease disease;

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
    public Long getId() {
        return this.id;
    }


}
