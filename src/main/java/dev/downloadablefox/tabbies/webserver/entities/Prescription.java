package dev.downloadablefox.tabbies.webserver.entities;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Entity
public class Prescription {
    @Id
    @GeneratedValue
    private Integer id;

    private Integer quantity;
    private String notes;

    @OneToOne
    @JoinColumn(name = "pet_id", unique = true, nullable = false) // pet_id es clave foránea y única
    private Pet pet;

    @OneToOne
    @JoinColumn(name = "medicine_id", unique = true, nullable = false) // medicine_id es clave foránea y única
    private Medicine medicine;

    @ManyToOne
    @JoinColumn(name = "veterinary_id")
    private Veterinary veterinary;


    public Prescription(Integer id, Integer quantity, String notes, Pet pet, Medicine medicine, Veterinary veterinary) {
        this.id = id;
        this.quantity = quantity;
        this.notes = notes;
        this.pet = pet;
        this.medicine = medicine;
        this.veterinary = veterinary;
    }
    public Prescription(Integer quantity, String notes , Pet pet, Medicine medicine, Veterinary veterinary) {
        this.quantity = quantity;
        this.notes = notes;
        this.pet = pet;
        this.medicine = medicine;
        this.veterinary = veterinary;
    }
    public Prescription() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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