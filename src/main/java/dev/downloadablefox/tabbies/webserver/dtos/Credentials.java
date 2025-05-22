package dev.downloadablefox.tabbies.webserver.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Credentials {
    private String email;
    private String password;    


    public String toString() {
        return "Credentials[email=" + email + ", password=" + password + "]";
    }
}
