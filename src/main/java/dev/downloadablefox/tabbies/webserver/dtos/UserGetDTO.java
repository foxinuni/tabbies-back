package dev.downloadablefox.tabbies.webserver.dtos;

import dev.downloadablefox.tabbies.webserver.entities.User;

public class UserGetDTO {
    private Long id;
    private int document;
    private String name;
    private String email;
    private Long number;

    public UserGetDTO(Long id, int document, String name, String email, Long number) {
        this.id = id;
        this.document = document;
        this.name = name;
        this.email = email;
        this.number = number;
    }

    public Long getId() {
        return id;
    }

    public int getDocument() {
        return document;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public Long getNumber() {
        return number;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setDocument(int document) {
        this.document = document;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setNumber(Long number) {
        this.number = number;
    }
}
