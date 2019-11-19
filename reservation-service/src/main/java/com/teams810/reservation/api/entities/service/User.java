package com.teams810.reservation.api.entities.service;

public class User extends ServiceEntity {
    private String username;
    private String firstname;
    private String lastname;
    private String role;

    public User() {
    }

    public String getUsername() {
        return username;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getRole() {
        return role;
    }
}
