package dev.downloadablefox.tabbies.webserver.entities;

public enum RoleType {
    USER(new Role("user")),
    VETERINARY(new Role("veterinary")),
    ADMIN(new Role("admin"));

    private final Role role;

    RoleType(Role role) {
        this.role = role;
    }

    public Role getRole() {
        return role;
    }
}