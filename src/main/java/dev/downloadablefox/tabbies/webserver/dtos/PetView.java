package dev.downloadablefox.tabbies.webserver.dtos;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PetView {
    private Long id;
    private String name;
    private String breed;
    private float weight;
    private String picture;
    private LocalDate birthDate;
    private Long ownerId;
    private boolean disabled;

    public String toString() {
        return "PetView[id=" + id + ", name=" + name + ", breed=" + breed + ", weight=" + weight + ", picture=" + picture + ", birthDate=" + birthDate + ", ownerId=" + ownerId + ", isDisabled=" + disabled + "]";
    }
}
