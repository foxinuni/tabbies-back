package dev.downloadablefox.tabbies.webserver.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class VeterinarianView {
    private Long id;
    private String email;
    private String role;
    private String name;
    private Integer document;
    private Long number;
    private String speciality;
    private String picture;
}
