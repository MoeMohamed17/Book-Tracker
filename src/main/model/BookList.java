package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;

//A class that contains a list of books
public class BookList implements Writable {
    private ArrayList<Book> books;

    //Constructs a list of books
    public BookList() {
        books = new ArrayList<>();
    }


    //MODIFIES: this
    //EFFECTS: adds a given book to the list of books
    public void addBook(Book b) {
        books.add(b);
    }

    //REQUIRES: list is not empty
    //EFFECTS: counts the number of books in the list
    public int countBooks() {
        int index = 0;
        for (Book b : books) {
            index++;
        }
        return index;
    }


    //EFFECTS: Produces a list of books and return null if there are no books
    public ArrayList<Book> allBooks() {
        ArrayList<Book> everyBook = new ArrayList<>();
        for (Book b : books) {
            everyBook.add(b);
        }
        if (everyBook.isEmpty()) {
            return null;
        } else {
            return everyBook;
        }
    }


    //EFFECTS: Produces a list of books that have not been read.
    //return null if there are no books that are unread.
    public ArrayList<Book> unReadBooks() {
        ArrayList<Book> notRead = new ArrayList<>();
        for (Book b : books) {
            if (b.getStatus().equals("unread")) {
                notRead.add(b);
            }
        }
        if (notRead.isEmpty()) {
            return null;
        } else {
            return notRead;
        }
    }

    //REQUIRES: list is not empty
    //EFFECTS: counts the number of unread books
    public int countUnReadBooks() {
        int index = 0;
        for (Book b : books) {
            if (b.getStatus().equals("unread")) {
                index++;
            }
        }
        return index;
    }


    //EFFECTS: Produces a list of books that have been read.
    //return null if there are no books that are read.
    public ArrayList<Book> readBooks() {
        ArrayList<Book> read = new ArrayList<>();
        for (Book b : books) {
            if (b.getStatus().equals("read")) {
                read.add(b);
            }
        }
        if (read.isEmpty()) {
            return null;
        } else {
            return read;
        }
    }

    //REQUIRES: list is not empty
    //EFFECTS: counts the number of read books
    public int countReadBooks() {
        int index = 0;
        for (Book b : books) {
            if (b.getStatus().equals("read")) {
                index++;
            }
        }
        return index;
    }


    //EFFECTS: Produces a list of books by genre and returns null if
    // no books of that genre are found
    public ArrayList<Book> booksByGenre(String genre) {
        ArrayList<Book> genreBooks = new ArrayList<>();
        for (Book b : books) {
            if (b.getGenre().equals(genre)) {
                genreBooks.add(b);
            }
        }
        if (genreBooks.isEmpty()) {
            return null;
        } else {
            return genreBooks;
        }
    }

    public void markBookAsRead(String title) {
        for (Book book : unReadBooks()) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                book.markAsReread();
                return;
            }
        }
        //System.out.println("Book not found in your reading list.");
    }


    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        //json.put("name", name);
        json.put("books", bookListToJson());
        return json;
    }

    // EFFECTS: returns things in this BookList as a JSON array
    private JSONArray bookListToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Book b : books) {
            jsonArray.put(b.toJson());
        }
        return jsonArray;
    }
}
