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

import javax.crypto.Cipher;

public class DataFile_manager {
    private static DataFile_manager df_manager = new DataFile_manager();

    public static DataFile_manager getInstance() {
        return df_manager;
    }
    //When given context, writes all people's data to a txt file.
    public void writeFile(Context context) {
        try {
            OutputStreamWriter osw = new OutputStreamWriter(context.openFileOutput("Data_file.txt", Context.MODE_PRIVATE));
            String s = "";
            osw.write(s);
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
            String s = "";
            while ((s=br.readLine())!=null) {
                //readline
            }
            in.close();
        }
        catch (IOException e) {
            Log.e("IOException", "Error in input");
        }
    }
}
