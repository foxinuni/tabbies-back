package dev.downloadablefox.tabbies.webserver.entities;

import java.sql.Date;

public class User {
    private Integer id;
    private String name;
    private String breed;
    private float weight;
    private String picture;
    private Date birthdate;
    
    public User(Integer id, String name, String breed, float weight, String picture, Date birthdate) {
        this.id = id;
        this.name = name;
        this.breed = breed;
        this.weight = weight;
        this.picture = picture;
        this.birthdate = birthdate;
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

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    
}