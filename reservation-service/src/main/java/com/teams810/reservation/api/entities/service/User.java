package com.teams810.reservation.api.entities.service;

public class User extends ServiceEntity {
    private String username;
    private String firstname;
    private String lastname;
    private String role;

    public User() {
    }

    public User(String username, String firstname, String lastname, String role) {
        this.username = username;
        this.firstname = firstname;
        this.lastname = lastname;
        this.role = role;
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
