package dev.downloadablefox.tabbies.webserver.entities;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Prescription {
    @Id
    @GeneratedValue
    private Integer id;

    private Integer quantity;
    private String notes;

    public Prescription(Integer id, Integer quantity, String notes) {
        this.id = id;
        this.quantity = quantity;
        this.notes = notes;
    }
    public Prescription(Integer quantity, String notes) {
        this.quantity = quantity;
        this.notes = notes;
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

}