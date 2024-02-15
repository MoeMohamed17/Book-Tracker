package model;

import java.util.ArrayList;

public class BookList {
    private ArrayList<Book> books;

    public BookList() {
        books = new ArrayList<>();
    }

    public void addBook(Book b) {
        books.add(b);
    }

    public int countBooks() {
        int index = 1;
        for (Book b : books) {
            index++;
        }
        return index;
    }

    public ArrayList<Book> unReadBooks() {
        ArrayList<Book> notRead = new ArrayList<>();
        for (Book b : books) {
            if (b.getStatus() == "unread") {
                notRead.add(b);
            }
        }
        return notRead;
    }

    public int countUnReadBooks() {
        int index = 1;
        for (Book b : books) {
            if (b.getStatus() == "unread") {
                index++;
            }
        }
        return index;
    }

    public ArrayList<Book> readBooks() {
        ArrayList<Book> read = new ArrayList<>();
        for (Book b : books) {
            if (b.getStatus() == "read") {
                read.add(b);
            }
        }
        return read;
    }

    public int countReadBooks() {
        int index = 1;
        for (Book b : books) {
            if (b.getStatus() == "read") {
                index++;
            }
        }
        return index;
    }

}
