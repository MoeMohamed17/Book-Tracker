package ui;

import java.io.FileNotFoundException;
import javax.swing.SwingUtilities;

// runs the program in terminal
//public class Main {
//    public static void main(String[] args) {
//        try {
//            new BookTrackApp();
//        } catch (FileNotFoundException e) {
//            System.out.println("Unable to run application: file not found");
//        }
//    }
//}


public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                new BookTrackGUI();
            } catch (Exception e) {
                System.out.println("Unable to run application: file not found");
            }
        });
    }
}
