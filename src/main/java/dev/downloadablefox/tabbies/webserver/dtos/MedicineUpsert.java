package dev.downloadablefox.tabbies.webserver.dtos;

import lombok.Data;

@Data
public class MedicineUpsert {
    private String name;
    private Double buyPrice;
    private Double sellPrice;
    private Integer stock;
    private Integer sold;

    public MedicineUpsert(String name, Double buyPrice, Double sellPrice, Integer stock, Integer sold) {
        this.name = name;
        this.buyPrice = buyPrice;
        this.sellPrice = sellPrice;
        this.stock = stock;
        this.sold = sold;
    }

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
