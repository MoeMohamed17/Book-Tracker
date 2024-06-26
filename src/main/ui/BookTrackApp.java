package ui;

import model.Book;
import model.BookList;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

// Inspired by TellerApp
// this class runs the interface
public class BookTrackApp  {
    private static final String JSON_STORE = "./data/BookList.json";
    private BookList bookList;
    private Scanner input;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    //EFFECTS: runs the BookTrack application
    public BookTrackApp() throws FileNotFoundException {
        runBookTrack();
    }

    //MODIFIES: this
    //Effects: processes user input
    public void runBookTrack() {
        boolean keepGoing = true;
        String command = null;

        init();

        while (keepGoing) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }

        System.out.println("\nGoodbye!");
    }

    //MODIFIES: this
    //EFFECTS: initializes BookList
    private void init() {
        bookList = new BookList();
        input = new Scanner(System.in);
        input.useDelimiter("\n");
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
    }

    //EFFECTS: displays menu of options to users
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\ta -> Add a new book");
        System.out.println("\tx -> Mark a book as read");
        System.out.println("\tr -> List read books");
        System.out.println("\tu -> List unread books");
        System.out.println("\tg -> Find a book by genre");
        System.out.println("\ts -> Save BookList to file");
        System.out.println("\tl -> Load BookList from file");
        System.out.println("\tq -> quit");
    }

    //MODIFIES: this
    //EFFECTS: processes user command
    private void processCommand(String command) {
        if (command.equals("a")) {
            doAddBook();
        } else if (command.equals("x")) {
            doMarkAsRead();
        } else if (command.equals("r")) {
            doReadBooks();
        } else if (command.equals("u")) {
            doUnreadBooks();
        } else if (command.equals("g")) {
            doGenre();
        } else if (command.equals("s")) {
            saveBookList();
        } else if (command.equals("l")) {
            loadBookList();
        } else {
            System.out.println("Selection not valid...");
        }
    }

    //MODIFIES: this
    //EFFECTS: creates a new book and adds it the BookList
    private void doAddBook() {
        System.out.println("\t -> What is the title of the book?");
        String t = input.next();
        System.out.println("\t -> What is the genre of the book?");
        String g = input.next();
        System.out.println("\t -> Who is the author of the book?");
        String a = input.next();
        System.out.println("\t -> What is the literary form of the book?");
        String f = input.next();
        System.out.println("\t -> What is the intended audience of the book?");
        String au = input.next();
        Book b = new Book(t, g, a, f, au);
        bookList.addBook(b);
    }

    //MODIFIES: this
    //EFFECTS: returns the title of all unread books. Tells you if no books are found
    private void doUnreadBooks() {
        if (bookList.unReadBooks() == null) {
            System.out.println("No books found, you're so smart!");
        } else {
            for (Book b : bookList.unReadBooks()) {
                System.out.println(b.getTitle());
            }
        }
    }

    //MODIFIES: this
    //EFFECTS: returns the title of all read books. Tells you if no books are found
    private void doReadBooks() {
        if (bookList.readBooks() == null) {
            System.out.println("No books found, you should read more!");
        } else {
            for (Book b : bookList.readBooks()) {
                System.out.println(b.getTitle());
            }
        }
    }

    //MODIFIES: this
    //EFFECTS: changes the status of the book that the user has read
    private void doMarkAsRead() {
        System.out.println("\t -> What book did you read?");
        String t = input.next();
        if (bookList.unReadBooks() == null) {
            System.out.println("That book is not in your reading list, stop skipping steps!");
        } else {
            for (Book b : bookList.unReadBooks()) {
                if (b.getTitle().equals(t)) {
                    b.markAsReread();
                }
            }
        }
    }

    //MODIFIES: this
    //EFFECTS: searches for books based on inputted genre and returns unread books of that genre
    private void doGenre() {
        System.out.println("\t -> What genre of book are you interested in?");
        String t = input.next();
        if (bookList.booksByGenre(t) == null) {
            System.out.println("No books found!");
        } else {
            for (Book b : bookList.booksByGenre(t)) {
                System.out.println(b.getTitle());
            }
        }
    }

    // EFFECTS: saves the BookList to file
    public void saveBookList() {
        try {
            jsonWriter.open();
            jsonWriter.write(bookList);
            jsonWriter.close();
            System.out.println("Saved BookList to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads workroom from file
    public void loadBookList() {
        try {
            bookList = jsonReader.read();
            System.out.println("Loaded BookList from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }
}




