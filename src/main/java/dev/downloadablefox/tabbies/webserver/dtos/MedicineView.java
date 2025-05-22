package dev.downloadablefox.tabbies.webserver.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MedicineView {
    private Long id;
    private String name;
    private Double buyPrice;
    private Double sellPrice;
    private Integer stock;
    private Integer sold;

    @Override
    public String toString() {
        return "MedicineView{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", buyPrice=" + buyPrice +
                ", sellPrice=" + sellPrice +
                ", stock=" + stock +
                ", sold=" + sold +
                '}';
    }
}
