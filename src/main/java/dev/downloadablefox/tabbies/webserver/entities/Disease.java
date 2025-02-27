package dev.downloadablefox.tabbies.webserver.entities;

public class Disease {

    private Integer id;
    private String name;
    private String description;
    private String procedure;

    
    public Disease(Integer id, String name, String description, String procedure) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.procedure = procedure;
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