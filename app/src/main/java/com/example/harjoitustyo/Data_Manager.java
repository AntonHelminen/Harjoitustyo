package com.example.harjoitustyo;

import java.util.HashMap;

/*Manages and calculates all data collected from all users.
Is Singleton*/
public class Data_Manager {
    private static Data_Manager data_Manager= new Data_Manager();
    Person_manager person_manager = Person_manager.getInstance();
    HashMap<String, Person> people = person_manager.getPeopleMap();
    //A multi-purpose HashMap variable
    HashMap<String, String> habits;

    public static Data_Manager getInstance() {
        return data_Manager;
    }
    //Finds the least commonly sorted waste type
    public String least_sorted_waste() {
        String result = "";
        int least_sorted_value = 3;
        int value;
        for (String key: people.keySet()) {
            Person person = people.get(key);
            habits = person.getHabits();
            for (String key2: habits.keySet()) {
                String amount = habits.get(key2);
                value = converter(amount);
                if (!(key2.equals("estimate"))) {
                    if (value < least_sorted_value) {
                        least_sorted_value = value;
                        result = key2;
                    }
                }
            }
        }
        return result;
    }
    //Finds the most commonly sorted waste type
    public String most_sorted_waste() {
        String result = "";
        int most_sorted_value = 0;
        int value;
        for (String key: people.keySet()) {
            Person person = people.get(key);
            habits = person.getHabits();
            for (String key2: habits.keySet()) {
                String amount = habits.get(key2);
                value = converter(amount);
                if (!(key2.equals("estimate"))) {
                    if (value > most_sorted_value) {
                        most_sorted_value = value;
                        result = key2;
                    }
                }

            }
        }
        return result;
    }
    //Converts habit into a simple Integer value.
    public int converter(String amount) {
        int result;
        if (amount.equals("never")) {
            result = 0;
        }
        else if (amount.equals("sometimes")) {
            result = 1;
        }
        else if (amount.equals("often")) {
            result = 2;
        }
        else if (amount.equals("always")) {
            result = 3;
        }
        else {
            if (amount.equals("low")) {
                result = 1;
            }
            else if (amount.equals("normal")) {
                result = 2;
            }
            else {
                result = 3;
            }
        }
        return result;
    }
    //Finds the waste type with biggest impact on the person's carbon trace.
    public String worst_waste_type(Person person) {
        String result = "";
        Double worst_amount = 0.0;
        Double value;
        HashMap<String, Double> info = carbon_trace_per_waste_type(person);
        for (String key : info.keySet()) {
            value = info.get(key);
            if (value > worst_amount) {
                worst_amount = value;
                result = key;
            }
        }
        return result;
    }
    //Reads a person's waste recycling habits and calculates the weight of every habit.
    public HashMap<String, Double> carbon_trace_per_waste_type(Person person) {
        Double[] info = evaluator();
        String habit;
        int value;
        int i = 0;
        HashMap<String, Double> results = new HashMap<>();
        habits = person.getHabits();
        for (String key: habits.keySet()) {
            habit = habits.get(key);
            value = converter(habit);
            results.put(key, value*info[i]);
            i ++;
        }
        return results;
    }
    //Absolute unit
    public Double[] evaluator() {
        API_reader reader = API_reader.getInstance();
        Double[] results = new Double[9];
        String a = "always";
        String b = "never";
        String c = "low";
        String d = "high";
        Double noSort = Double.valueOf(reader.getJSON2(b,b,b,b,b,b,b,b,c));
        Double biowaste = Double.valueOf(reader.getJSON2(a,b,b,b,b,b,b,b,c));
        Double carton = Double.valueOf(reader.getJSON2(b,a,b,b,b,b,b,b,c));
        Double electronic = Double.valueOf(reader.getJSON2(b,b,a,b,b,b,b,b,c));
        Double glass = Double.valueOf(reader.getJSON2(b,b,b,a,b,b,b,b,c));
        Double hazardous = Double.valueOf(reader.getJSON2(b,b,b,b,a,b,b,b,c));
        Double metal = Double.valueOf(reader.getJSON2(b,b,b,b,b,a,b,b,c));
        Double paper = Double.valueOf(reader.getJSON2(b,b,b,b,b,b,a,b,c));
        Double plastic = Double.valueOf(reader.getJSON2(b,b,b,b,b,b,b,a,c));
        Double estimate = Double.valueOf(reader.getJSON2(b,b,b,b,b,b,b,b,d));
        Double biowaste_per_choice = (noSort-biowaste)/3;
        Double carton_per_choice = (noSort-carton)/3;
        Double electronic_per_choice = (noSort-electronic)/3;
        Double glass_per_choice = (noSort-glass)/3;
        Double hazardous_per_choice = (noSort-hazardous)/3;
        Double metal_per_choice = (noSort-metal)/3;
        Double paper_per_choice = (noSort-paper)/3;
        Double plastic_per_choice = (noSort-plastic)/3;
        Double estimate_per_choice = (noSort-estimate)/3;
        results[0] = biowaste_per_choice;
        results[1] = carton_per_choice;
        results[2] = electronic_per_choice;
        results[3] = glass_per_choice;
        results[4] = hazardous_per_choice;
        results[5] = metal_per_choice;
        results[6] = paper_per_choice;
        results[7] = plastic_per_choice;
        results[8] = estimate_per_choice;

        return results;
    }
    //Calculates users' recycling habits per age-group. Returns an array of results.
    public int[] sorting_by_age() {
        int[] results = new int[5];
        int value;
        System.out.println("Amount of people: " + people.size());
        for (String key : people.keySet()) {
            System.out.println("Reading a person");
            Person person = people.get(key);
            person_manager.personToString(person);
            value = person_habits_to_value(person);
            int age = person.getAge();
            if (age <= 18) {
                results[0] = results[0] + value;
            }
            else if (age > 18 && age <= 35) {
                results[1] = results[1] + value;
            }
            else if (age > 35 && age <= 50) {
                results[2] = results[2] + value;
            }
            else if (age > 50 && age <= 65) {
                results[3] = results[3] + value;
            }
            else if (age > 65) {
                results[4] = results[4] + value;
            }
        }
        System.out.println("indeksi 0: " + results[0]);
        System.out.println("indeksi 1: " + results[1]);
        System.out.println("indeksi 2: " + results[2]);
        System.out.println("indeksi 3: " + results[3]);
        System.out.println("indeksi 4: " + results[4]);
        return results;
    }
    //Used for turning a person's recycling habits into a simple Integer value.
    public int person_habits_to_value(Person person) {
        int value = 0;
        habits = person.getHabits();
        for (String key : habits.keySet()) {
            String habit = habits.get(key);
            if (!(habit.equals(null))) {
                value += converter(habit);
            }
        }
        System.out.println("Value of person's habits: " + value);
        return value;
    }
}
