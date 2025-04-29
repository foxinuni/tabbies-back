package dev.downloadablefox.tabbies.webserver.entities;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

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

    public Procedure() {}
    
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }  

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }

    public Medicine getMedicine() {
        return medicine;
    }
    
    public void setMedicine(Medicine medicine) {
        this.medicine = medicine;
    }
    
    public Veterinary getVeterinary() {
        return veterinary;
    }

    public void setVeterinary(Veterinary veterinary) {
        this.veterinary = veterinary;
    }
}