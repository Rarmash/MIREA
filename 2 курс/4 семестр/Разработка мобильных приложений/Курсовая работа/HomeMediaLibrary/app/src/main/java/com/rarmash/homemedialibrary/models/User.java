package com.rarmash.homemedialibrary.models;

public class User {
    String email, username, password;
    boolean superUser;

    public User(String email, String username, String password, boolean superUser) {
        this.email = email;
        this.username = username;
        this.password = password;
        this.superUser = superUser;
    }

    public User(String email, boolean superUser) {
        this.email = email;
        this.superUser = superUser;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean getSuperUser() {
        return superUser;
    }

    public void setSuperUser(boolean superUser) {
        this.superUser = superUser;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
