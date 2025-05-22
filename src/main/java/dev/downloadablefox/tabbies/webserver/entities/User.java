package dev.downloadablefox.tabbies.webserver.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Builder;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "users")
public class User extends UserEntity {
    @Column(nullable = false, unique = true)
    private int document;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private Long number;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Pet> pets = new ArrayList<>();

    // Builder personalizado para solo los campos que necesitas (sin id ni pets)
    @Builder
    public User(String email, String hash, Role role, int document, String name, Long number) {
        super(email, hash, role);

        this.document = document;
        this.name = name;
        this.number = number;
    }

    public User(Long id, String email, String hash, Role role, int document, String name, Long number) {
        super(id, email, hash, role);

        this.document = document;
        this.name = name;
        this.number = number;
    }
}
