package dev.downloadablefox.tabbies.webserver.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;


@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
public abstract class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    public String email;

    @Column(nullable = false)
    public String hash;

    @ManyToOne(optional = false)
    @JoinColumn(name = "role_id", nullable = false)
    public Role role;

    public UserEntity(String email, String hash, Role role) {
        this.email = email;
        this.hash = hash;
        this.role = role;
    }

    public UserEntity(Long id, String email, String hash, Role role) {
        this.id = id;
        this.email = email;
        this.hash = hash;
        this.role = role;
    }
}
