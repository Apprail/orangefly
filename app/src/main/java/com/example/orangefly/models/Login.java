package com.example.orangefly.models;

public class Login {

    private String username;
    private String name;
    private String salt;

    public String getUsername() {
        return username;
    }

    public String getName() {
        return name;
    }

    public String getSalt() {
        return salt;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }
}
