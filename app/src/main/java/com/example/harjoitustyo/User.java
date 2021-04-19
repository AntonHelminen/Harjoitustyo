package com.example.harjoitustyo;

/* User is a simple data class with a person attached to it. Can be called everywhere.
Is Singleton*/
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

