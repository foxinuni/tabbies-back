package dev.downloadablefox.tabbies.webserver.dtos;

public class VeterinarianUpsert {
    private String name;
    private String email;
    private Integer document;
    private Long number;
    private String role;
    private String speciality;
    private String picture;

    public VeterinarianUpsert() {}

    public VeterinarianUpsert(String name, String email, Integer document, Long number, String role, String speciality, String picture) {
        this.name = name;
        this.email = email;
        this.document = document;
        this.number = number;
        this.role = role;
        this.speciality = speciality;
        this.picture = picture;
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

    public Integer getDocument() {
        return document;
    }

    public void setDocument(Integer document) {
        this.document = document;
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
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
}
