package dev.downloadablefox.tabbies.webserver.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class MedicineUpsert {
    private String name;
    private Double buyPrice;
    private Double sellPrice;
    private Integer stock;
    private Integer sold;

    @Override
    public String toString() {
        return "MedicineUpsert{" +
                "name='" + name + '\'' +
                ", buyPrice=" + buyPrice +
                ", sellPrice=" + sellPrice +
                ", stock=" + stock +
                ", sold=" + sold +
                '}';
    }
}
