package com.cosc426.passwordmanager;

//Class object stores name of website and its password
public class Password {
    private String name, password;

    public Password(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }
}
