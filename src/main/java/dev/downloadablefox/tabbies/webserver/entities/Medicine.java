package dev.downloadablefox.tabbies.webserver.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

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

    public Medicine() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getBuyPrice() {
        return buyPrice;
    }

    public void setBuyPrice(Double buyPrice) {
        this.buyPrice = buyPrice;
    }

    public Double getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(Double sellPrice) {
        this.sellPrice = sellPrice;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Integer getSold() {
        return sold;
    }

    public void setSold(Integer sold) {
        this.sold = sold;
    }
}