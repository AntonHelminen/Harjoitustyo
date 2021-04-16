package com.example.harjoitustyo;

import java.util.HashMap;

public class Person_manager {
    private static Person_manager manager = new Person_manager();
    HashMap<String, Person> people = new HashMap();

    public static Person_manager getInstance() {
        return manager;
    }
    //Add a person to the list
    public void addPerson(String username, String name, String password, int age) {
        Person person = new Person(username, name, password, age);
        people.put(password, person);
    }
    //Person finder by password and username
    public Person getPerson(String password, String username) {
        Person person;
        person = people.get(password);
        return person;
    }
}
