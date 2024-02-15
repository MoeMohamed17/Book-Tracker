package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BookTest {

    private Book testBook;

    @BeforeEach
    void runBefore() {
        testBook = new Book("the great gatsby", "Novel", "F. scott fitzgerald", "Fiction", "Adult");
    }

    @Test
    void testMarkAsRead() {
        assertEquals("unread", testBook.getStatus());
        testBook.markAsReread();
        assertEquals("read", testBook.getStatus());
    }

    @Test
    void testUpdateTitle() {
        assertEquals("the great gatsby", testBook.getTitle());
        testBook.updateTitle("The Great Gatsby");
        assertEquals("The Great Gatsby", testBook.getTitle());
    }

    @Test
    void testUpdateGenre() {
        assertEquals("Novel", testBook.getGenre());
        testBook.updateGenre("Tragedy");
        assertEquals("Tragedy", testBook.getGenre());
    }

    @Test
    void testUpdateAuthor() {
        assertEquals("F. scott fitzgerald", testBook.getAuthor());
        testBook.updateAuthor("F. Scott Fitzgerald");
        assertEquals("F. Scott Fitzgerald", testBook.getAuthor());
    }

    @Test
    void testUpdateForm() {
        assertEquals("Fiction", testBook.getForm());
        testBook.updateForm("Literary Fiction");
        assertEquals("Literary Fiction", testBook.getForm());
    }

    @Test
    void testUpdateAudience() {
        assertEquals("Adult", testBook.getAudience());
        testBook.updateAudience("Student");
        assertEquals("Student", testBook.getAudience());
    }


}
