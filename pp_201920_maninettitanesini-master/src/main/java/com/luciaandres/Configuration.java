package com.luciaandres;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;


public class Configuration {

    private String inputFile;

    public Configuration(String inputFile) {
        this.inputFile = inputFile;
    }

    public int getPageSize() {
        InputStream file = getClass().getClassLoader().getResourceAsStream(inputFile);
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file))) {
            int number = Integer.parseInt(reader.readLine());
            if (number < 1) throw new Exception();

            return number;
        } catch (Exception e) {
            return 1;
        }
    }
}
