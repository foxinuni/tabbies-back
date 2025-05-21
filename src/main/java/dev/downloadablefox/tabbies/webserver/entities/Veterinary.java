package dev.downloadablefox.tabbies.webserver.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Veterinary{
    @Id
    @GeneratedValue
    private Long id;
    
    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, unique = true)
    private Integer document;

    @Column(nullable = false, unique = true)
    private Long number;

    @Column(nullable = false)
    private String role;

    @Column(nullable = false)
    private String speciality;

    @Column(nullable = false)
    private String picture;

    public Veterinary(String role, String speciality, String picture, Integer document, String name, String email,
            Long number) {
        this.role = role;
        this.speciality = speciality;
        this.picture = picture;
        this.document = document;
        this.name = name;
        this.email = email;
        this.number = number;
    }
}
