package com.example.harjoitustyo;

public class Person {
    //Login info
    private String username;
    private String password;
    //Other info
    private String name;
    private String home_town;
    private int age;
    public Person(String username, String password, String name, int age) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.age = age;
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
    //Setters()
    public void setUsername(String username) {
        this.username = username;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setHome_town(String home) {
        this.home_town = home;
    }
    public void setAge(int age) {
        this.age = age;
    }

}
