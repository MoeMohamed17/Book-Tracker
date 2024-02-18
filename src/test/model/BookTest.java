package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

//Test Book Class methods
public class BookTest {

    private Book testBook;

    @BeforeEach
    void runBefore() {
        testBook = new Book("the great gatsby", "Novel", "F. scott fitzgerald", "Fiction", "Adult");
    }

    @Test
    void testMarkAsRead() {
        assertEquals("unread", testBook.getStatus()); //books starts off as unread
        testBook.markAsReread(); // Change the status to read
        assertEquals("read", testBook.getStatus()); //check to see if book status changed
    }

    @Test
    void testUpdateTitle() {
        assertEquals("the great gatsby", testBook.getTitle()); //get the title of the book
        testBook.updateTitle("The Great Gatsby"); // change the title name
        assertEquals("The Great Gatsby", testBook.getTitle()); // check to see if title has been changed
    }

    @Test
    void testUpdateGenre() {
        assertEquals("Novel", testBook.getGenre()); // get the genre of the book
        testBook.updateGenre("Tragedy"); // change the genre
        assertEquals("Tragedy", testBook.getGenre()); // see if the genre has been changed
    }

    @Test
    void testUpdateAuthor() {
        assertEquals("F. scott fitzgerald", testBook.getAuthor()); // get the author of the book
        testBook.updateAuthor("F. Scott Fitzgerald"); // change the author
        assertEquals("F. Scott Fitzgerald", testBook.getAuthor()); // see if the author has been changed
    }

    @Test
    void testUpdateForm() {
        assertEquals("Fiction", testBook.getForm()); //get the literary form of the book
        testBook.updateForm("Literary Fiction"); // change the literary form
        assertEquals("Literary Fiction", testBook.getForm()); // see if the form has changed
    }

    @Test
    void testUpdateAudience() {
        assertEquals("Adult", testBook.getAudience()); // get the target audience
        testBook.updateAudience("Student"); // change the audience
        assertEquals("Student", testBook.getAudience()); // see if the audience has changed
    }


}
