package dev.downloadablefox.tabbies.webserver.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserView {
    private Long id;
    private int document;
    private String name;
    private String email;
    private Long number;

    public String toString() {
        return "UserView[id=" + id + ", document=" + document + ", name=" + name + ", email=" + email + ", number=" + number + "]";
    }
}
