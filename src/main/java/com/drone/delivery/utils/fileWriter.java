package com.drone.delivery.utils;

import java.io.IOException;
import java.io.FileWriter;


public class fileWriter {
    /**
     This Method writes the file to given location
     */
    public void writeTofile(String[] arr, String path) throws IOException {
        FileWriter writer = new FileWriter(path);
        for(String str: arr) {
            writer.write(str + System.lineSeparator());
        }
        writer.close();
    }
}
