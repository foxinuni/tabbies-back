package dev.downloadablefox.tabbies.webserver.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Veterinary extends UserEntity {
    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private Integer document;

    @Column(nullable = false, unique = true)
    private Long number;

    @Column(nullable = false)
    private String speciality;

    @Column(nullable = false)
    private String picture;

    public Veterinary(String email, String hash, Role role, String speciality, String picture, Integer document, String name, Long number) {
        super(email, hash, role);

        this.speciality = speciality;
        this.picture = picture;
        this.document = document;
        this.name = name;
        this.email = email;
        this.number = number;
    }

    public Veterinary(Long id, String email, String hash, Role role, String speciality, String picture, Integer document, String name, Long number) {
        super(id, email, hash, role);

        this.speciality = speciality;
        this.picture = picture;
        this.document = document;
        this.name = name;
        this.number = number;
    }
}
