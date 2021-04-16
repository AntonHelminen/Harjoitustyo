package com.example.harjoitustyo;

public class User {
    private static User user = new User();
    private String id;
    private String user_name;
    private Person person;
    public static User getInstance() {
        System.out.println("ha");
        return user;

    }
}
