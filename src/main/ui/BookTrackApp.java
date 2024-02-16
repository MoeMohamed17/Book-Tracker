package ui;

import model.Book;
import model.BookList;

import java.util.ArrayList;
import java.util.Scanner;

//inspired by TellerApp
public class BookTrackApp {
    private BookList bookList;
    private Scanner input;


    public BookTrackApp() {
        runBookTrack();
    }

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

    private void init() {
        bookList = new BookList();
        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }

    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\ta -> Add a new book");
        System.out.println("\tx -> Mark a book as read");
        System.out.println("\tr -> List read books");
        System.out.println("\tu -> List unread books");
        System.out.println("\tg -> Find a book by genre");
        System.out.println("\tq -> quit");
    }

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
        } else {
            System.out.println("Selection not valid...");
        }
    }

    private void doAddBook() {
        System.out.println("\td -> What is the title of the book?");
        String t = input.next();
        System.out.println("\td -> What is the genre of the book?");
        String g = input.next();
        System.out.println("\td -> Who is the author of the book?");
        String a = input.next();
        System.out.println("\td -> What is the literary form of the book?");
        String f = input.next();
        System.out.println("\td -> What is the intended audience of the book?");
        String au = input.next();
        Book b = new Book(t, g, a, f, au);
        bookList.addBook(b);
    }

    private void doUnreadBooks() {
        if (bookList.unReadBooks() == null) {
            System.out.println("No books found, you're so smart!");
        } else {
            for (Book b : bookList.unReadBooks()) {
                System.out.println(b.getTitle());
            }
        }
    }

    private void doReadBooks() {
        if (bookList.readBooks() == null) {
            System.out.println("No books found, you should read more!");
        } else {
            for (Book b : bookList.readBooks()) {
                System.out.println(b.getTitle());
            }
        }
    }


    private void doMarkAsRead() {
        System.out.println("\td -> What book did you read?");
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

    private void doGenre() {
        System.out.println("\td -> What genre of book are you interested in?");
        String t = input.next();
        if (bookList.unReadBooks() == null) {
            System.out.println("No books found!");
        } else {
            for (Book b : bookList.booksByGenre(t)) {
                System.out.println(b.getTitle());
            }
        }
    }
}




