package dev.downloadablefox.tabbies.webserver.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProcedureView {
    private Long id;
    private Integer quantity;
    private String notes;
    private Long petId;
    private Long medicineId;
    private Long veterinaryId;

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
