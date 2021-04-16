package com.example.harjoitustyo;

public class Person {
    //Login info
    private String username;
    private String password;
    //Other info
    private String name;
    private String home_town;
    private int age;
    public Person(String username, String name, String password, int age) {

    }
    //Getters()
    public String getUsername() {
        return this.username;
    }
    public String getPassword() {
        return this.password;
    }
    public String getName() {
        return this.name;
    }
    public String getHome_town() {
        return this.home_town;
    }
    public int getAge() {
        return this.age;
    }

}
