package dev.downloadablefox.tabbies.webserver.dtos;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PetUpsert {
    private String name;
    private String breed;
    private float weight;
    private String picture;
    private LocalDate birthDate;
    private Long ownerId;
    private Boolean disabled;


    public String toString() {
        return "PetUpsert[name=" + name + ", breed=" + breed + ", weight=" + weight + ", picture=" + picture + ", birthDate=" + birthDate + ", isDisabled=" + disabled + ", ownerId=" + ownerId + "]";
    }
}
