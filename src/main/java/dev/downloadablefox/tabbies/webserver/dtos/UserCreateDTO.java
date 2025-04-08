package dev.downloadablefox.tabbies.webserver.dtos;

import dev.downloadablefox.tabbies.webserver.entities.User;

public class UserCreateDTO {
    private int document;
    private String name;
    private String email;
    private Long number;
    private String password;

    public UserCreateDTO() {}

    public UserCreateDTO(int document, String name, String email, Long number, String password) {
        this.document = document;
        this.name = name;
        this.email = email;
        this.number = number;
        this.password = password;
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

    public String getPassword() {
        return password;
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

    public void setPassword(String password) {
        this.password = password;
    }
}
