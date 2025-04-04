package dev.downloadablefox.tabbies.webserver.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Veterinary{
    @Id
    @GeneratedValue
    private Long id;
    
    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, unique = true)
    private Integer document;

    @Column(nullable = false, unique = true)
    private Long number;

    @Column(nullable = false)
    private String role;

    @Column(nullable = false)
    private String speciality;

    @Column(nullable = false)
    private String picture;

    public Veterinary(String role, String speciality, String picture, Integer document, String name, String email,
            Long number) {
        this.role = role;
        this.speciality = speciality;
        this.picture = picture;
        this.document = document;
        this.name = name;
        this.email = email;
        this.number = number;
    }

    public Veterinary() {}

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
    
    public Long getNumber() {
        return number;
    }
    
    public void setNumber(Long number) {
        this.number = number;
    }
}
