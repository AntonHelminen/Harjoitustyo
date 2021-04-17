package com.example.harjoitustyo;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class API_reader {
    private static API_reader api_reader= new API_reader();
    //Variables for calculating
    private String bioWaste = "never";
    private String carton = "never";
    private String electronic = "never";
    private String glass = "never";
    private String hazardous = "never";
    private String metal = "never";
    private String paper = "never";
    private String plastic = "never";
    private String estimate = "low";
    //Result
    private double result = 0.0;
    public static API_reader getInstance() {
        return api_reader;
    }
    //Setters, getters not needed. (Setters could be replaced by public variables)
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
    //Getter for result (just in case)
    public double getResult() {
        return result;
    }
    //Calculation method
    public double calculate() {
        String json = getJSON();
        result = Double.valueOf(json);
        return result;
    }
    public String getJSON() {
        String response = null;
        String Url = "https://ilmastodieetti.ymparisto.fi/ilmastodieetti/calculatorapi/v1/WasteCalculator?query.bioWaste=" + bioWaste + "&query.carton=" + carton + "&query.electronic=" + electronic + "&query.glass=" + glass + "&query.hazardous=" + hazardous + "&query.metal=" + metal + "&query.paper=" + paper + "&query.plastic=" + plastic + "&query.amountEstimate=" + estimate;
        System.out.println(Url);
        try {
            URL url = new URL(Url);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            InputStream in = new BufferedInputStream(connection.getInputStream());
            BufferedReader br = new BufferedReader((new InputStreamReader(in)));
            StringBuilder sb = new StringBuilder();
            String line = null;
            while((line = br.readLine()) != null) {
                sb.append(line).append("\n");
            }
            response = sb.toString();
            in.close();
        } catch (MalformedURLException | ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }

}
