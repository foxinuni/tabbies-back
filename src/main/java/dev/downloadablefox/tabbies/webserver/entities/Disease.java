package dev.downloadablefox.tabbies.webserver.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Disease {

    @Id
    @GeneratedValue
    private Integer idDisease;

    private String name;
    private String description;
    private String procedure;

    
    public Disease(Integer id, String name, String description, String procedure) {
        this.idDisease = id;
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
        return idDisease;
    }


    public void setId(Integer id) {
        this.idDisease = id;
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