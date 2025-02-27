package dev.downloadablefox.tabbies.webserver.entities;

public class Prescription {
    private Integer id;
    private Integer quantity;
    private String notes;

    public Prescription(Integer id, Integer quantity, String notes) {
        this.id = id;
        this.quantity = quantity;
        this.notes = notes;
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