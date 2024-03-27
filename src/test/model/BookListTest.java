package model;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

// A class to test the methods in BookList
public class BookListTest {

    private BookList testBooks;
    private Book B1;
    private Book B2;
    private Book B3;

    @BeforeEach
    void runBefore() {
        testBooks = new BookList();
        B1 = new Book("The Great Gatsby", "Novel", "F. Scott Fitzgerald", "Fiction", "Adult");
        B2 = new Book("Animal Farm", "Satire", "George Orwell", "Fable", "Adult");
        B3 = new Book("Can't Hurt Me", "Biography", "David Goggins", "Non-fiction", "Adult");
    }

    @Test
    void testAddBook() {
        testBooks.addBook(B1);
        testBooks.addBook(B2);
        assertEquals(2, testBooks.countBooks()); // check to see if there are 2 books added
        assertEquals(B1, testBooks.allBooks().get(0)); // shows that the books are be added in correct order
        testBooks.addBook(B3);
        assertEquals(3, testBooks.countBooks()); // check to see if number of book changes
        assertEquals(B3, testBooks.allBooks().get(2)); //check to see if it was added to the end
    }

    @Test
    void testCountBooks() {
        testBooks.addBook(B1);
        testBooks.addBook(B2);
        assertEquals(2, testBooks.countBooks()); // check to see if there are 2 books added
        testBooks.addBook(B3);
        assertEquals(3, testBooks.countBooks()); // see if number of books changed after adding another one
    }

    @Test
    void testAllBooks() {
        assertEquals(null, testBooks.allBooks()); // checks to see if BookList is empty
        testBooks.addBook(B1);
        testBooks.addBook(B2);
        assertEquals(B1, testBooks.allBooks().get(0)); // checks to see if books are added in the correct order
        assertEquals(B2, testBooks.allBooks().get(1)); // checks to see if books are added in the correct order
        testBooks.addBook(B3); // add a new book
        assertEquals(B3, testBooks.allBooks().get(2)); // checks to see if books are added in the correct order
    }

    @Test
    void testUnReadBooks() {
        testBooks.addBook(B1);
        testBooks.addBook(B2);
        assertEquals(B1, testBooks.unReadBooks().get(0)); // checks to see if books are added in the correct order
        assertEquals(B2, testBooks.unReadBooks().get(1)); // checks to see if books are added in the correct order
        assertEquals(2, testBooks.countUnReadBooks()); // checks to see if both books were added to unread list
        testBooks.addBook(B3);
        assertEquals(3, testBooks.countUnReadBooks());
        assertEquals(B3, testBooks.unReadBooks().get(2));
        B1.markAsReread(); // change the status to read
        assertEquals(B2, testBooks.unReadBooks().get(0)); // check to see if it got removed from unread list
        assertEquals(2, testBooks.countUnReadBooks());
        B2.markAsReread(); // change the status to read
        assertEquals(B3, testBooks.unReadBooks().get(0)); // check to see if it got removed from unread list
        assertEquals(1, testBooks.countUnReadBooks());
        B3.markAsReread(); // change the status to read
        assertEquals(null, testBooks.unReadBooks()); // check to see if the list is now empty
    }

    @Test
    void testReadBooks() {
        testBooks.addBook(B1);
        testBooks.addBook(B2);
        assertEquals(null, testBooks.readBooks()); // List should be empty because there are no read books
        B1.markAsReread(); // change the status to read
        assertEquals(B1, testBooks.readBooks().get(0)); // see if there are now books that have been read
        assertEquals(1, testBooks.countReadBooks());
        B2.markAsReread(); // change the status to read
        assertEquals(B2, testBooks.readBooks().get(1)); // see if there are now books that have been read
        assertEquals(2, testBooks.countReadBooks());
        testBooks.addBook(B3); // add a new book
        assertEquals(2, testBooks.countReadBooks()); // that book hasn't been added to read list
        B3.markAsReread(); // change the status to read
        assertEquals(B3, testBooks.readBooks().get(2)); // see if there are now books that have been read
        assertEquals(3, testBooks.countReadBooks());
    }

    @Test
    void testCountUnReadBooks() {
        testBooks.addBook(B1);
        testBooks.addBook(B2);
        assertEquals(2, testBooks.countUnReadBooks());
        testBooks.addBook(B3);
        assertEquals(3, testBooks.countUnReadBooks());
        B1.markAsReread();
        B2.markAsReread();
        assertEquals(1, testBooks.countUnReadBooks());
    }

    @Test
    void testCountReadBooks() {
        testBooks.addBook(B1);
        testBooks.addBook(B2);
        B1.markAsReread();
        assertEquals(1, testBooks.countReadBooks());
        B2.markAsReread();
        assertEquals(2, testBooks.countReadBooks());
        testBooks.addBook(B3);
        assertEquals(2, testBooks.countReadBooks());
        B3.markAsReread();
        assertEquals(3, testBooks.countReadBooks());
    }

    @Test
    void testBooksByGenre() {
        testBooks.addBook(B1);
        testBooks.addBook(B2);
        assertEquals(B1, testBooks.booksByGenre("Novel").get(0));
        assertEquals(null, testBooks.booksByGenre("Biography")); // no books found
        testBooks.addBook(B3);
        assertEquals(B3, testBooks.booksByGenre("Biography").get(0)); // now it found it
        Book B4 = new Book("A Promised Land", "Biography", "Barack Obama", "Non-fiction", "Adult");
        testBooks.addBook(B4);
        assertEquals(B4, testBooks.booksByGenre("Biography").get(1));
    }

    @Test
    void testMarkBookAsRead() {
        testBooks.addBook(B1);
        testBooks.addBook(B2);
        testBooks.markBookAsRead("The Great Gatsby");
        assertEquals("read", B1.getStatus());
        assertEquals("unread", B2.getStatus());
        testBooks.markBookAsRead("Moe");
        assertEquals("unread", B2.getStatus());
    }
}
