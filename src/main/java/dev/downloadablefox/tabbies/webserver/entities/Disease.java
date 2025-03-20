package dev.downloadablefox.tabbies.webserver.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

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
    public Disease() {
    }

    public Integer getId() {
        return id;
    }


    public void setId(Integer id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public String getDescription() {
        return description;
    }


    public void setDescription(String description) {
        this.description = description;
    }


    public String getProcedure() {
        return procedure;
    }


    public void setProcedure(String procedure) {
        this.procedure = procedure;
    }

}