package com.example.harjoitustyo;

import java.util.HashMap;

public class Person_manager {
    private DataFile_manager dataFile_manager = DataFile_manager.getInstance();
    private static Person_manager manager = new Person_manager();
    private HashMap<String, Person> people = dataFile_manager.getPeopleMap();

    public static Person_manager getInstance() {
        return manager;
    }
    //Add a person to the list
    public void addPerson(String username, String name, String password, int age) {
        Person person = new Person(username, name, password, age);
        people.put(password, person);
        System.out.println("Person added!");
    }
    //Person getter by password and username returns (person) or (null)
    public Person getPerson(String password, String username) {
        if(findPerson(password, username)) {
            Person person = people.get(password);
            System.out.println("Person got!");
            return person;
        }
        return null;
    }
    //person finder by password and username, returns (true) or (false) boolean value
    public boolean findPerson(String password, String username) {
        Person person = people.get(password);
        if (person == null) {
            return false;
        }
        else {
            if (person.getUsername().equals(username)) {
                System.out.println("Person found!");
                return true;
            }
        }
        return false;
    }
    //Removes a specific person
    public void removePerson(String password, String username) {
        if(findPerson(password, username)) {
            people.remove(password);
            System.out.println("Person removed!");
        }
    }
    public HashMap getPeopleMap() {
        return people;
    }
}
