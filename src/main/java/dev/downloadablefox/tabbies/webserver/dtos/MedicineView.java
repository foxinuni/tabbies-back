package dev.downloadablefox.tabbies.webserver.dtos;

public class MedicineView {
    private Long id;
    private String name;
    private Double buyPrice;
    private Double sellPrice;
    private Integer stock;
    private Integer sold;

    public MedicineView(Long id, String name, Double buyPrice, Double sellPrice, Integer stock, Integer sold) {
        this.id = id;
        this.name = name;
        this.buyPrice = buyPrice;
        this.sellPrice = sellPrice;
        this.stock = stock;
        this.sold = sold;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Double getBuyPrice() {
        return buyPrice;
    }

    public Double getSellPrice() {
        return sellPrice;
    }

    public Integer getStock() {
        return stock;
    }

    public Integer getSold() {
        return sold;
    }

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
