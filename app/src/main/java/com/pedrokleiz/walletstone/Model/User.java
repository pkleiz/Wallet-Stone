package com.pedrokleiz.walletstone.Model;

public class User {
    private String email, password;

    public User() {
    }

    public String getEmail() { return email;}

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
