package dev.downloadablefox.tabbies.webserver.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VeterinarianUpsert {
    private String name;
    private String email;
    private String password;
    private Integer document;
    private Long number;
    private String role;
    private String speciality;
    private String picture;
}
