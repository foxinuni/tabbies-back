package dev.downloadablefox.tabbies.webserver.dtos;

public class ProcedureView {
    private Long id;
    private Integer quantity;
    private String notes;
    private Long petId;
    private Long medicineId;
    private Long veterinaryId;

    public ProcedureView(Long id, Integer quantity, String notes, Long petId, Long medicineId, Long veterinaryId) {
        this.id = id;
        this.quantity = quantity;
        this.notes = notes;
        this.petId = petId;
        this.medicineId = medicineId;
        this.veterinaryId = veterinaryId;
    }

    public Long getId() {
        return id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public String getNotes() {
        return notes;
    }

    public Long getPetId() {
        return petId;
    }

    public Long getMedicineId() {
        return medicineId;
    }

    public Long getVeterinaryId() {
        return veterinaryId;
    }

    @Override
    public String toString() {
        return "ProcedureView{" +
                "id=" + id +
                ", quantity=" + quantity +
                ", notes='" + notes + '\'' +
                ", petId=" + petId +
                ", medicineId=" + medicineId +
                ", veterinaryId=" + veterinaryId +
                '}';
    }
}
