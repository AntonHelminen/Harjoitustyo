package com.example.harjoitustyo;


import android.content.Intent;

import java.util.regex.Pattern;

class Login_Manager {
    private static Login_Manager login_checker = new Login_Manager();
    private Person_manager person_manager = Person_manager.getInstance();
    private Person person;
    private User user = User.getInstance();
    public static Login_Manager getInstance() {
        return login_checker;
    }
    // Used for checking if a person is on the created list
    public boolean find_person(String password, String username) {
        if (person_manager.findPerson(password, username)) {
            person = person_manager.getPerson(password, username);
            return true;
        }
        return false;
    }
    // used for creating a new person in SignUpActivity
    public boolean createPerson(String username, String password, String name, int age) {
        Boolean hasdigit = false;
        Boolean hasUpper = false;
        Boolean hasLower = false;
        Boolean hasSpecial = false;
        Boolean is_long_enough = false;
        if (password.length() >= 12) {
            is_long_enough = true;
        }
        if (!(Pattern.matches("[a-zA-Z]+", password))) {
            hasSpecial = true;

        }
        char[] chars = password.toCharArray();
        for (char c: chars) {
            if (Character.isDigit(c)) {
                hasdigit = true;
            }
            if (Character.isUpperCase(c)) {
                hasUpper = true;
            }
            if (Character.isLowerCase(c)) {
                hasLower = true;
            }

        }
        if (hasdigit && hasUpper && hasLower && hasSpecial && is_long_enough) {
            person = new Person(username, password, name, age);
            person_manager.addPerson(person);
            System.out.println("Account created");
            return true;
        }
        else {
            if (hasdigit) {
            }
            else {
                System.out.println("Doesn't contain a digit.");
            }
            if (hasUpper) {
            }
            else {
                System.out.println("Doesn't contain an uppercase letter.");
            }
            if (hasLower) {
            }
            else {
                System.out.println("Doesn't contain an lowercase letter.");
            }
            if (hasSpecial) {
            }
            else {
                System.out.println("Doesn't contain a special character.");
            }
            if (is_long_enough) {
            }
            else {
                System.out.println("Is not long enough. Must be at least 12 characters.");
            }
            return false;
        }



    }
    // login checker
    public boolean login(String password, String username) {
        if (find_person(password, username)) {
            user.setPerson(person);
            return true;
        }
        else {
            return false;
        }
    }
}