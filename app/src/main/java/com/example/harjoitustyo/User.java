package com.example.harjoitustyo;

// User is a
public class User {
    private static User user = new User();
    private Person person;
    public static User getInstance() {
        return user;
    }
}
