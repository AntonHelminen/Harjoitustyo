package com.example.harjoitustyo;

import java.util.ArrayList;
import java.util.HashMap;

// A basic storage class with all sorts of data collected from a user.
public class Person {
    //Login info
    private String username;
    private String password;
    //Other info
    private String name;
    private String home_town;
    private int age;
    //Calculation info
    private ArrayList<Double> C02 = new ArrayList<>();
    private HashMap<String, String> habits = new HashMap<>();
    private String bioWaste;
    private String carton;
    private String electronic;
    private String glass;
    private String hazardous;
    private String metal;
    private String paper;
    private String plastic;
    private String estimate;

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
    public ArrayList getC02() {
        return this.C02;
    }
    public String getBioWaste() {
        return bioWaste;
    }
    public String getCarton() {
        return carton;
    }
    public String getElectronic() {
        return electronic;
    }
    public String getGlass() {
        return glass;
    }
    public String getHazardous() {
        return hazardous;
    }
    public String getMetal() {
        return metal;
    }
    public String getPaper() {
        return paper;
    }
    public String getPlastic() {
        return plastic;
    }
    public String getEstimate() {
        return estimate;
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
    public void setC02(Double value) {
        C02.add(value);
    }
    public void setBioWaste(String s) {
        bioWaste = s;
    }
    public void setCarton(String s) {
        carton = s;
    }
    public void setElectronic(String s) {
        electronic = s;
    }
    public void setGlass(String s) {
        glass = s;
    }
    public void setHazardous(String s) {
        hazardous = s;
    }
    public void setMetal(String s) {
        metal = s;
    }
    public void setPaper(String s) {
        paper = s;
    }
    public void setPlastic(String s) {
        plastic = s;
    }
    public void setEstimate(String s) {
        estimate = s;
    }
    //Data resetter for C02
    public void reset() {
        C02.clear();
    }
    //Habit setter for list
    public void setHabits() {
        habits.put("bioWaste", bioWaste);
        habits.put("carton", carton);
        habits.put("electronic", electronic);
        habits.put("glass", glass);
        habits.put("hazardous", hazardous);
        habits.put("metal", metal);
        habits.put("paper", paper);
        habits.put("plastic", plastic);
        habits.put("estimate", estimate);
    }
    public HashMap<String, String> getHabits() {
        return habits;
    }

}
