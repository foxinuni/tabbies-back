package dev.downloadablefox.tabbies.webserver.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Medicine {
    @Id
    @GeneratedValue
    private Long id;
    
    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Double buyPrice;

    @Column(nullable = false)
    private Double sellPrice;
    
    @Column(nullable = false)
    private Integer stock;

    @Column(nullable = false)
    private Integer sold;

    public Medicine(Long id, String name, Double buyPrice, Double sellPrice, Integer stock, Integer sold) {
        this.id = id;
        this.name = name;
        this.buyPrice = buyPrice;
        this.sellPrice = sellPrice;
        this.stock = stock;
        this.sold = sold;
    }

    public Medicine(String name, Double buyPrice, Double sellPrice, Integer stock, Integer sold) {
        this.name = name;
        this.buyPrice = buyPrice;
        this.sellPrice = sellPrice;
        this.stock = stock;
        this.sold = sold;
    }

}