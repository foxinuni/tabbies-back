package dev.downloadablefox.tabbies.webserver.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "Dueños")
public class User {
    
    private int document;
    private String name;
    private String email;
    private String hash;
    private Long number;

    @OneToMany(mappedBy = "owner")
    private List<Pet> pets = new ArrayList<>();

    @Id
    @GeneratedValue
    private Long id;

    public User(Long id, int document, String name, String email, String hash, Long number) {
        this.id = id;
        this.document = document;
        this.name = name;
        this.email = email;
        this.hash = hash;
        this.number = number;
    }

    public User(int document, String name, String email, String hash, Long number) {
        this.document = document;
        this.name = name;
        this.email = email;
        this.hash = hash;
        this.number = number;
    }

    public User() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getDocument() {
        return document;
    }

    public void setDocument(int document) {
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

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getHash() {
        return hash;
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    } 

    public String toString() {
        return "User [id=" + id + ", document=" + document + ", name=" + name + ", email=" + email + ", hash=" + hash + ", number=" + number + "]";
    }

    public List<Pet> getPets() {
        return pets;
    }
    public void setPets(List<Pet> pets) {
        this.pets = pets;
    }
}