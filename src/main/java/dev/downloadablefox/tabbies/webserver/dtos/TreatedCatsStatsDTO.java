package dev.downloadablefox.tabbies.webserver.dtos;

public class TreatedCatsStatsDTO {
    private long treatedCatsCount;

    public TreatedCatsStatsDTO(long treatedCatsCount) {
        this.treatedCatsCount = treatedCatsCount;
    }

    public long getTreatedCatsCount() {
        return treatedCatsCount;
    }

    public void setTreatedCatsCount(long treatedCatsCount) {
        this.treatedCatsCount = treatedCatsCount;
    }
}
