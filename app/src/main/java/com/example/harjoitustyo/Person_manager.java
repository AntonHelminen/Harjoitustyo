package com.example.harjoitustyo;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
/*Manages all people. User may collect a person from here as well as other classes.
Is Singleton*/
public class Person_manager {
    private static Person_manager manager = new Person_manager();
    private HashMap<String, Person> people = new HashMap<>();

    public static Person_manager getInstance() {
        return manager;
    }
    //Add a person to the list
    public void addPerson(Person person) {
        people.put(person.getPassword(), person);
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
    //Get HashMap from Person_manager
    public HashMap getPeopleMap() {
        return people;
    }
    // Old DataFile_manager methods
    public void writeFile(Context context) {
        try {
            OutputStreamWriter osw = new OutputStreamWriter(context.openFileOutput("Data_file.txt", Context.MODE_PRIVATE));
            for (String key : people.keySet()){
                Person person = people.get(key);
                String line = person.getUsername() + ";" + person.getPassword() + ";" +  person.getName() + ";" + person.getAge() + ";" + person.getHome_town();
                int i = 0;
                String line2 = "";
                while (i < person.getC02().size()) {
                    line2 += person.getC02().get(i).toString();
                    i ++;
                }
                String line3 = person.getBioWaste() + ";" + person.getCarton() + ";" + person.getElectronic() + ";" + person.getGlass() + ";" + person.getHazardous() + ";" + person.getMetal() + ";" + person.getPaper() + ";" + person.getPlastic() + ";" + person.getEstimate();
                osw.write(line);
                osw.write(line2);
                osw.write(line3);
            }
        }
        catch (IOException e) {
            Log.e("IOException", "Error in input");
        }
    }
    //When given context, reads all people's data from a txt file and puts it in a HashMap
    public void readFile(Context context) {
        try {
            InputStream in = context.openFileInput("Data_file.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String line;
            people.clear();
            while ((line=br.readLine())!=null) {
                String[] parts = line.split(";");
                String username = parts[0];
                String password = parts[1];
                String name = parts[2];
                int age = Integer.valueOf(parts[3]);
                String home = parts[4];
                Person person = new Person(username, password, name, age);
                person.setHome_town(home);
                people.put(password, person);
            }
            in.close();
        }
        catch (IOException e) {
            Log.e("IOException", "Error in input");
        }
    }
}
