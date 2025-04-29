package dev.downloadablefox.tabbies.webserver.dtos;

public class ProcedureUpsert {
    private Integer quantity;
    private String notes;
    private Long petId;
    private Long medicineId;
    private Long veterinaryId;

    public ProcedureUpsert(Integer quantity, String notes, Long petId, Long medicineId, Long veterinaryId) {
        this.quantity = quantity;
        this.notes = notes;
        this.petId = petId;
        this.medicineId = medicineId;
        this.veterinaryId = veterinaryId;
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
        return "ProcedureUpsert{" +
                "quantity=" + quantity +
                ", notes='" + notes + '\'' +
                ", petId=" + petId +
                ", medicineId=" + medicineId +
                ", veterinaryId=" + veterinaryId +
                '}';
    }
}
