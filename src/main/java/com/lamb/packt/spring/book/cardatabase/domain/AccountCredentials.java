package com.lamb.packt.spring.book.cardatabase.domain;

public class AccountCredentials {
    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public AccountCredentials setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public AccountCredentials setPassword(String password) {
        this.password = password;
        return this;
    }
}
