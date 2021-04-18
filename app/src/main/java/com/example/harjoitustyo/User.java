package com.example.harjoitustyo;

// User is a singleton class with person attached to it. Can be called everywhere.
public class User {
    private static User user = new User();
    private Person person;
    public static User getInstance() {
        return user;
    }
    public void setPerson(Person persona) {
        person = persona;
    }
    public Person getPerson() {
        return person;
    }
}

