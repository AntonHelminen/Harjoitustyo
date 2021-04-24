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
                value = converter_for_recycling_amount(amount);
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
                value = converter_for_recycling_amount(amount);
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
    //Converts habit into a simple Integer value. This one calculates the amount of recycling
    //activity
    public int converter_for_recycling_amount(String amount) {
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
    //Converts habit into a simple Integer value. This one calculates the effect on C02 emissions.
    public int converter_for_C02_weight(String amount) {
        int result;
        if (amount.equals("never")) {
            result = 3;
        }
        else if (amount.equals("sometimes")) {
            result = 2;
        }
        else if (amount.equals("often")) {
            result = 1;
        }
        else if (amount.equals("always")) {
            result = 0;
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
        String result_value = "";
        HashMap<String, Double> info = carbon_trace_per_waste_type(person);
        for (String key : info.keySet()) {
            value = info.get(key);
            if (value > worst_amount) {
                worst_amount = value;
                result = key;
            }
        }
        result_value = worst_amount.toString();
        return result + ";" +result_value;
    }
    //Reads a person's waste recycling habits and calculates the weight of every habit.
    public HashMap<String, Double> carbon_trace_per_waste_type(Person person) {
        Double[] info = evaluator();
        System.out.println(info.length);
        System.out.println("0: " + info[0] + " 1: " + info[1] + " 2: " + info [2] + "3: " + info[3] + "4: " + info[4] + " 5: " + info[5] + " 6: " + info [6] + "7: " + info[7]);
        String habit;
        int value;
        HashMap<String, Double> results = new HashMap<>();
        habits = person.getHabits();
        for (String key: habits.keySet()) {
            if (key.equals("bioWaste")) {
                habit = habits.get(key);
                value = converter_for_C02_weight(habit);
                results.put(key, value*info[0]);
            }
            else if (key.equals("carton")) {
                habit = habits.get(key);
                value = converter_for_C02_weight(habit);
                results.put(key, value*info[1]);
            }
            else if (key.equals("electronic")) {
                habit = habits.get(key);
                value = converter_for_C02_weight(habit);
                results.put(key, value*info[2]);
            }
            else if (key.equals("glass")) {
                habit = habits.get(key);
                value = converter_for_C02_weight(habit);
                results.put(key, value*info[3]);
            }
            else if (key.equals("hazardous")) {
                habit = habits.get(key);
                value = converter_for_C02_weight(habit);
                results.put(key, value*info[4]);
            }
            else if (key.equals("metal")) {
                habit = habits.get(key);
                value = converter_for_C02_weight(habit);
                results.put(key, value*info[5]);
            }
            else if (key.equals("paper")) {
                habit = habits.get(key);
                value = converter_for_C02_weight(habit);
                results.put(key, value*info[6]);
            }
            else if (key.equals("plastic")) {
                habit = habits.get(key);
                value = converter_for_C02_weight(habit);
                results.put(key, value*info[7]);
            }
        }
        return results;
    }
    /*This method was used to determine the effects of recycling each type of waste. Data was read
    from the API by only selecting one type of waste per time. That one waste type was selected to
    be always recycled and others never to be recycled. By comparing the results with a value that
    was gotten by not recycling anything, it is possible to determine the effects of each recycling
    habit by waste type.
    It is not possible to read all data live in one run, since opening too many URL connections in a short
    time seems to cause a crash. Therefore the data was collected and stored to these parameters
    over a number of runs.
    Edit: it seems only 2 reads can be done per run.*/
    public Double[] evaluator() {
        API_reader reader = API_reader.getInstance();
        Double[] results = new Double[8];
        String a = "always";
        String b = "never";
        String c = "low";

        //First run:
        //Double noSort_test = Double.valueOf(reader.getJSON2(b,b,b,b,b,b,b,b,c));
        //Double biowaste_test = Double.valueOf(reader.getJSON2(a,b,b,b,b,b,b,b,c));
        //Results:
        Double noSort = 106.8672;
        Double biowaste = 75.47496;
        //System.out.println(noSort_test + " : " + biowaste_test);

        //Second run:
        //Double carton_test = Double.valueOf(reader.getJSON2(b,a,b,b,b,b,b,b,c));
        //Double electronic_test = Double.valueOf(reader.getJSON2(b,b,a,b,b,b,b,b,c));
        //Results:
        Double carton = 98.5182;
        Double electronic = 103.19364;
        //System.out.println(carton_test + " : " + electronic_test);

        //Third run:
        //Double glass_test = Double.valueOf(reader.getJSON2(b,b,b,a,b,b,b,b,c));
        //Double hazardous_test = Double.valueOf(reader.getJSON2(b,b,b,b,a,b,b,b,c));
        //Results:
        Double glass = 101.8578;
        Double hazardous = 103.5276;
        //System.out.println(glass_test + " : " + hazardous_test);

        //Fourth run:
        //Double metal_test = Double.valueOf(reader.getJSON2(b,b,b,b,b,a,b,b,c));
        //Double paper_test = Double.valueOf(reader.getJSON2(b,b,b,b,b,b,a,b,c));
        //Results:
        Double metal = 101.18988;
        Double paper = 89.16732;
        //System.out.println(metal_test + " : " + paper_test);

        //Fifth run:
        //Double plastic_test = Double.valueOf(reader.getJSON2(b,b,b,b,b,b,b,a,c));
        //Results:
        Double plastic = 93.84276;
        //System.out.println(plastic_test);

        Double biowaste_per_choice = (noSort-biowaste)/3;
        Double carton_per_choice = (noSort-carton)/3;
        Double electronic_per_choice = (noSort-electronic)/3;
        Double glass_per_choice = (noSort-glass)/3;

        Double hazardous_per_choice = (noSort-hazardous)/3;
        Double metal_per_choice = (noSort-metal)/3;
        Double paper_per_choice = (noSort-paper)/3;
        Double plastic_per_choice = (noSort-plastic)/3;
        results[0] = biowaste_per_choice;
        results[1] = carton_per_choice;
        results[2] = electronic_per_choice;
        results[3] = glass_per_choice;
        results[4] = hazardous_per_choice;
        results[5] = metal_per_choice;
        results[6] = paper_per_choice;
        results[7] = plastic_per_choice;

        return results;
    }
    //Calculates users' recycling habits per age-group. Returns an array of results.
    public Double[] sorting_by_age() {
        Double[] results = new Double[5];
        int a = 0;
        while (a < results.length) {
            results[a] = 0.0;
            a++;
        }
        Double value;
        int i0 = 0;
        int i1 = 0;
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        System.out.println("Amount of people: " + people.size());
        for (String key : people.keySet()) {
            System.out.println("Reading a person");
            Person person = people.get(key);
            System.out.println(person_manager.personToString(person));
            value = (person_habits_to_value(person))*1.0;
            int age = person.getAge();
            System.out.println("\n\n\nAge: " + age);
            if (age <= 18) {
                System.out.println("Bug1");
                results[0] = results[0] + value;
                i0 ++;
                System.out.println("Bug2");
            }
            else if (age > 18 && age <= 35) {
                results[1] = results[1] + value;
                i1 ++;
            }
            else if (age > 35 && age <= 50) {
                results[2] = results[2] + value;
                i2 ++;
            }
            else if (age > 50 && age <= 65) {
                results[3] = results[3] + value;
                i3 ++;
            }
            else if (age > 65) {
                results[4] = results[4] + value;
                i4 ++;
            }
        }
        if (i0 == 0) {
            results[0] = 0.0;
        }
        else {
            results[0] = results[0]/i0;
        }

        if (i1 == 0) {
            results[1] = 0.0;
        }
        else {
            results[1] = results[1]/i1;
        }

        if (i2 == 0) {
            results[2] = 0.0;
        }
        else {
            results[2] = results[2]/i2;
        }

        if (i3 == 0) {
            results[3] = 0.0;
        }
        else {
            results[3] = results[3]/i3;
        }

        if (i4 == 0) {
            results[4] = 0.0;
        }
        else {
            results[4] = results[4]/i4;
        }

        results[1] = results[1]/i1;
        results[2] = results[2]/i2;
        results[3] = results[3]/i3;
        results[4] = results[4]/i4;
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
        person.setHabits();
        habits = person.getHabits();
        for (String key : habits.keySet()) {
            String habit = habits.get(key);
            if (habit != null) {
                value += converter_for_recycling_amount(habit);
            }
        }
        System.out.println("Value of person's habits: " + value);
        return value;
    }
    public int totalUses() {
        int value = 0;
        for (String key : people.keySet()) {
            Person person = people.get(key);
            value += person.getTimes_used();
        }
        return value;
    }
}
