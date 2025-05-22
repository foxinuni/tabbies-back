package dev.downloadablefox.tabbies.webserver.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserUpsert {
    private int document;
    private String name;
    private String email;
    private Long number;
    private String password;

    public String toString() {
        return "UserUpsert[document=" + document + ", name=" + name + ", email=" + email + ", number=" + number + ", password=" + password + "]";
    }
}
