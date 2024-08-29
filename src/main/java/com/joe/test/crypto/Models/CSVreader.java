package com.joe.test.crypto.Models;

import java.util.*;
import java.io.*;

public class CSVreader {

    public static String symbolToFilename (String dir, String symbol)
    {
        String path = "/crypto/src/main/resources/data";
        return dir + path + "/" + symbol + "_values" + ".csv";
    }

    public static List<String[]> readFile(String filename) {

        List<String[]> lines = new ArrayList<>();

        try {
            File file = new File(filename);
            Scanner reader = new Scanner(file);
            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                String[] tokens = line.split(",");
                lines.add(tokens);
            }
            reader.close();
        } catch (FileNotFoundException e)
        {
            System.out.println("File not found "+filename);
            e.printStackTrace();
        }


        return lines;
    }
}
