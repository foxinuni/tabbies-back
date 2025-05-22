package dev.downloadablefox.tabbies.webserver.dtos;

public class CatCareTipDTO {
    private String tip;

    public CatCareTipDTO() {
    }

    public CatCareTipDTO(String tip) {
        this.tip = tip;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }
}
