package dev.downloadablefox.tabbies.webserver.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Setter;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Procedure {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private Integer quantity;

    @Column(nullable = false)
    private String notes;

    @OneToOne
    @JoinColumn(name = "pet_id", nullable = false) 
    private Pet pet;

    @OneToOne
    @JoinColumn(name = "medicine_id", nullable = true)
    private Medicine medicine;

    @ManyToOne
    @JoinColumn(name = "veterinary_id", nullable = false)
    private Veterinary veterinary;
    
    public Procedure(Long id, Integer quantity, String notes, Pet pet, Medicine medicine, Veterinary veterinary) {
        this.id = id;
        this.quantity = quantity;
        this.notes = notes;
        this.pet = pet;
        this.medicine = medicine;
        this.veterinary = veterinary;
    }

    public Procedure(Integer quantity, String notes, Pet pet, Medicine medicine, Veterinary veterinary) {
        this.quantity = quantity;
        this.notes = notes;
        this.pet = pet;
        this.medicine = medicine;
        this.veterinary = veterinary;
    }

    public Pet getPet() {
        return this.pet;
    }

}