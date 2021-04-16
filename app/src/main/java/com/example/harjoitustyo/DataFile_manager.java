package com.example.harjoitustyo;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;

import javax.crypto.Cipher;

public class DataFile_manager {
    private Person_manager person_manager = Person_manager.getInstance();
    private static DataFile_manager df_manager = new DataFile_manager();
    private HashMap<String, Person> people_write = new HashMap<>();
    private HashMap<String, Person> people_read = person_manager.getPeopleMap();
    public static DataFile_manager getInstance() {
        return df_manager;
    }
    //When given context, writes all people's data to a txt file.
    public void writeFile(Context context) {
        try {
            OutputStreamWriter osw = new OutputStreamWriter(context.openFileOutput("Data_file.txt", Context.MODE_PRIVATE));
            for (String key : people_read.keySet()){
                Person person = people_read.get(key);
                String line = person.getUsername() + ";" + person.getPassword() + ";" +  person.getName() + ";" + person.getAge() + ";" + person.getHome_town();
                osw.write(line);
            }
        }
        catch (IOException e) {
            Log.e("IOException", "Error in input");
        }
    }
    //When given context, reads all people's data from a txt file.
    public void readFile(Context context) {
        try {
            InputStream in = context.openFileInput("Data_file.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String line;
            people_write.clear();
            while ((line=br.readLine())!=null) {
                String[] parts = line.split(";");
                String username = parts[0];
                String password = parts[1];
                String name = parts[2];
                int age = Integer.valueOf(parts[3]);
                String home = parts[4];
                Person person = new Person(username, password, name, age);
                person.setHome_town(home);
                people_write.put(password, person);
            }
            in.close();
        }
        catch (IOException e) {
            Log.e("IOException", "Error in input");
        }
    }
    public HashMap getPeopleMap() {
        return people_write;
    }
}
