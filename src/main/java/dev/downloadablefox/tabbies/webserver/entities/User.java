package dev.downloadablefox.tabbies.webserver.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Builder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "Users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private int document;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String hash;

    @Column(nullable = false, unique = true)
    private Long number;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Pet> pets = new ArrayList<>();


    public User(int document, String name, String email, String hash, Long number) {
        this.document = document;
        this.name = name;
        this.email = email;
        this.hash = hash;
        this.number = number;
    }

    public User(Long id, int document, String name, String email, String hash, Long number) {
        this.id = id;
        this.document = document;
        this.name = name;
        this.email = email;
        this.hash = hash;
        this.number = number;
    }

}
