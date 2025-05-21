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
public class Disease {
    @Id
    @GeneratedValue
    private Integer id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String procedure;
    
    public Disease(Integer id, String name, String description, String procedure) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.procedure = procedure;
    }

    public Disease(String name, String description, String procedure) {
        this.name = name;
        this.description = description;
        this.procedure = procedure;
    }
}