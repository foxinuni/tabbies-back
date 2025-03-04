package dev.downloadablefox.tabbies.webserver.entities;

import java.util.ArrayList;
import java.util.List;

public class User {
    private Long id;
    private int document;
    private String name;
    private String email;
    private int number;
    private List<Long> pets;

    public User(Long id, int document, String name, String email, int number) {
        this.id = id;
        this.document = document;
        this.name = name;
        this.email = email;
        this.number = number;
        this.pets = new ArrayList<>();
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

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public List<Long> getPets() {
        return pets;
    }

    public void setPets(List<Long> pets) {
        this.pets = pets;
    }   
}