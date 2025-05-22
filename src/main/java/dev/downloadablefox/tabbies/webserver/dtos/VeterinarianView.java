package dev.downloadablefox.tabbies.webserver.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class VeterinarianView {
    private Long id;
    private String name;
    private String email;
    private Integer document;
    private Long number;
    private String role;
    private String speciality;
    private String picture;

}
