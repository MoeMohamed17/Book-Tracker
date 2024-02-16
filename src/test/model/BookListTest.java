package model;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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
        testBooks.addBook(B1);
        testBooks.addBook(B2);
    }

    @Test
    void testAddBook() {
        assertEquals(2, testBooks.countBooks());
        assertEquals(B1, testBooks.allBooks().get(0));
        testBooks.addBook(B3);
        assertEquals(3, testBooks.countBooks());
        assertEquals(B3, testBooks.allBooks().get(2));
    }

    @Test
    void testCountBooks() {
        assertEquals(2, testBooks.countBooks());
        testBooks.addBook(B3);
        assertEquals(3, testBooks.countBooks());
    }

    @Test
    void testAllBooks() {
        assertEquals(B1, testBooks.allBooks().get(0));
        assertEquals(B2, testBooks.allBooks().get(1));
        testBooks.addBook(B3);
        assertEquals(B3, testBooks.allBooks().get(2));
    }

    @Test
    void testUnReadBooks() {
        assertEquals(B1, testBooks.unReadBooks().get(0));
        assertEquals(B2, testBooks.unReadBooks().get(1));
        assertEquals(2, testBooks.countUnReadBooks());
        testBooks.addBook(B3);
        assertEquals(3, testBooks.countUnReadBooks());
        assertEquals(B3, testBooks.unReadBooks().get(2));
        B1.markAsReread();
        assertEquals(B2, testBooks.unReadBooks().get(0));
        assertEquals(2, testBooks.countUnReadBooks());
        B2.markAsReread();
        assertEquals(B3, testBooks.unReadBooks().get(0));
        assertEquals(1, testBooks.countUnReadBooks());
        B3.markAsReread();
        assertEquals(null, testBooks.unReadBooks());
    }

    @Test
    void testReadBooks() {
        assertEquals(null, testBooks.readBooks());
        B1.markAsReread();
        assertEquals(B1, testBooks.readBooks().get(0));
        assertEquals(1, testBooks.countReadBooks());
        B2.markAsReread();
        assertEquals(B2, testBooks.readBooks().get(1));
        assertEquals(2, testBooks.countReadBooks());
        testBooks.addBook(B3);
        assertEquals(2, testBooks.countReadBooks());
        B3.markAsReread();
        assertEquals(B3, testBooks.readBooks().get(2));
        assertEquals(3, testBooks.countReadBooks());
    }

    @Test
    void testCountUnReadBooks() {
        assertEquals(2, testBooks.countUnReadBooks());
        testBooks.addBook(B3);
        assertEquals(3, testBooks.countUnReadBooks());
        B1.markAsReread();
        B2.markAsReread();
        assertEquals(1, testBooks.countUnReadBooks());
    }

    @Test
    void testCountReadBooks() {
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
        assertEquals(B1, testBooks.booksByGenre("Novel").get(0));
        assertEquals(null, testBooks.booksByGenre("Biography"));
        testBooks.addBook(B3);
        assertEquals(B3, testBooks.booksByGenre("Biography").get(0));
        Book B4 = new Book("A Promised Land", "Biography", "Barack Obama","Non-fiction","Adult" );
        testBooks.addBook(B4);
        assertEquals(B4, testBooks.booksByGenre("Biography").get(1));
    }


}
