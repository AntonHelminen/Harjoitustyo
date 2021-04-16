package com.example.harjoitustyo;

import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class DataFile_manager {

    public DataFile_manager() {

    }
    public void writeFile() {
        try {

        }
        catch (IOException e) {
            Log.e("IOException", "Error in input");
        }
        catch (FileNotFoundException e) {
            Log.e("FileNotFoundException", "Could not find file");
        }
    }
    public void readFile() {
        try {
            InputStream in = null;
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String s = "";
            while ((s=br.readLine())!=null) {
                //readline
            }
        }
        catch (IOException e) {
            Log.e("IOException", "Error in input");
        }
        catch (FileNotFoundException e) {
            Log.e("FileNotFoundException", "Could not find file");
        }
    }
}
