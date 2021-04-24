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
    //Add a person to the list. Can't have two users with same password.
    public void addPerson(Person person) {
        people.put(person.getPassword(), person);
        System.out.println(person.getName());
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
            System.out.println(people.get(password).getName());
            people.remove(password);

            System.out.println("Person removed!");
        }
    }
    //Get HashMap from Person_manager
    public HashMap<String, Person> getPeopleMap() {
        return people;
    }
    // Old DataFile_manager methods
    public void writeFile(Context context) {
        try {

            OutputStreamWriter osw = new OutputStreamWriter(context.openFileOutput("Data_file.txt", Context.MODE_PRIVATE));
            for (String key : people.keySet()){
                Person person = people.get(key);
                System.out.println("File being written!");
                String line = person.getUsername() + ";" + person.getPassword() + ";" +  person.getName() + ";" + person.getAge() + ";";
                System.out.println(line);
                int i = 0;
                String line2 = "";
                if (i < person.getC02().size()) {
                    while (i < person.getC02().size()) {
                        line2 += person.getC02().get(i).toString();
                        if (i+1 < person.getC02().size()) {
                            line2 += ",";
                        }
                        i ++;
                    }
                }
                else {
                    line2 = "null";
                }
                String line3 = ";" + person.getBioWaste() + ";" + person.getCarton() + ";" + person.getElectronic() + ";" + person.getGlass() + ";" + person.getHazardous() + ";" + person.getMetal() + ";" + person.getPaper() + ";" + person.getPlastic() + ";" + person.getEstimate();
                String line4 = ";" + person.getFragment() + ";" + person.getTimes_used() + "\n";
                osw.write(line);
                osw.write(line2);
                osw.write(line3);
                osw.write(line4);
            }
            osw.close();
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
                System.out.println("File being read!");
                String[] parts = line.split(";");
                String username = parts[0];
                String password = parts[1];
                String name = parts[2];
                int age = Integer.valueOf(parts[3]);

                Person person = new Person(username, password, name, age);
                try {
                    person.setFragment(parts[14]);
                    person.setTimes_used(Integer.valueOf(parts[15]));
                }
                catch (IndexOutOfBoundsException e) {
                    e.printStackTrace();
                }


                //CO2 values
                if (!(parts[4].equals("null"))) {
                    System.out.println("CO2 being read!");
                    String[] parts2 = parts[4].split(",");
                    int i = 0;
                    while (i < parts2.length) {
                        person.setC02(Double.valueOf(parts2[i]));
                        i ++;
                    }
                }

                //These are empty at first reads, so must pay attention to null-pointer errors.
                if (!(parts[5].equals("null"))) {
                    System.out.println("Habits being read!");
                    person.setBioWaste(parts[5]);
                    person.setCarton(parts[6]);
                    person.setElectronic(parts[7]);
                    person.setGlass(parts[8]);
                    person.setHazardous(parts[9]);
                    person.setMetal(parts[10]);
                    person.setPaper(parts[11]);
                    person.setPlastic(parts[12]);
                    person.setEstimate(parts[13]);
                }
                people.put(password, person);
            }
            in.close();
        }
        catch (IOException e) {
            Log.e("IOException", "Error in input");
        }
    }
    public String personToString(Person person) {
        String data = "(-Basic info-)" +
                "\nUsername: " + person.getUsername() +
                "\nPassword: " + person.getPassword() +

                "\n\nActual name: " + person.getName() +
                "\nAge:" + person.getAge() +

                "\n\nData:" +
                "\nCO2 load: " + person.getC02() +
                "\nBiowaste: " +person.getBioWaste() +
                "\nCarton: " +person.getCarton() +
                "\nElectronics: " +person.getElectronic() +
                "\nGlass: " +person.getGlass() +
                "\nHazardous: " + person.getHazardous() +
                "\nMetal: " + person.getMetal() +
                "\nPaper: " + person.getPaper() +
                "\nPlastic: " + person.getPlastic() +

                "\n\nEstimate: " + person.getEstimate();
        return data;
    }
}
