package com.drone.delivery.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class fileReader {

    /**
     This Method reads the file fromgiven location and returns the String list
     */
    public static List<String> readFile(String filepath)throws IOException {

        File file = new File(filepath);
        BufferedReader br = new BufferedReader(new FileReader(file));
        String st;
        List<String> stL=new ArrayList<String>();
        while ((st = br.readLine()) != null) {
            stL.add(st);
        }
        return stL;
    }
}
