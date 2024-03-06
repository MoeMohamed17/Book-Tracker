package ui;

import java.io.FileNotFoundException;

// runs the program
public class Main {
    public static void main(String[] args) {
        try {
            new BookTrackApp();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to run application: file not found");
        }
    }
}
