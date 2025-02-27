package dev.downloadablefox.tabbies.webserver.entities;

public class Veterinary{
    private String role;
    private String speciality;
    private String picture;
    private Integer document;
    private String name;
    private String email;
    private Integer number;

    public Veterinary(String role, String speciality, String picture, Integer document, String name, String email,
            Integer number) {
        this.role = role;
        this.speciality = speciality;
        this.picture = picture;
        this.document = document;
        this.name = name;
        this.email = email;
        this.number = number;
    }

    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }
    public String getSpeciality() {
        return speciality;
    }
    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }
    public String getPicture() {
        return picture;
    }
    public void setPicture(String picture) {
        this.picture = picture;
    }
    public Integer getDocument() {
        return document;
    }
    public void setDocument(Integer document) {
        this.document = document;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public Integer getNumber() {
        return number;
    }
    public void setNumber(Integer number) {
        this.number = number;
    }    

}
