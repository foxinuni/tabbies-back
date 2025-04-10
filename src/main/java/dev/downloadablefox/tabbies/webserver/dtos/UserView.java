package dev.downloadablefox.tabbies.webserver.dtos;

public class UserView {
    private Long id;
    private int document;
    private String name;
    private String email;
    private Long number;

    public UserView(Long id, int document, String name, String email, Long number) {
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

    public String toString() {
        return "UserView[id=" + id + ", document=" + document + ", name=" + name + ", email=" + email + ", number=" + number + "]";
    }
}
