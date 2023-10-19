package pl.polsl.lab1.votesystem.fileMenager;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileMenager {
    public static List<List<String>> Reader(String file) {
        List<List<String>> myList = new ArrayList<>();
        try {
            BufferedReader in = new BufferedReader(new FileReader(file));
            while(in.ready()) {
                String line = in.readLine();
                String[] parts = line.split(" ");
                ArrayList<String> lineList = new ArrayList<>();
                for (String s : parts) {
                    lineList.add(s);
                }
                myList.add(lineList);
            }
        }
        catch(Exception e) {
            System.out.println("Error reading File");
        }
        return myList;
    }


    public static void addToFile(String user,String file) throws IOException {
        File add = new File(file);
        FileWriter fileWriter = new FileWriter(add, true);
        BufferedWriter write = new BufferedWriter(fileWriter);
        write.newLine();
        write.write(user);
        write.flush();
        write.close();
    }

    public static void toFile() {

    }
}