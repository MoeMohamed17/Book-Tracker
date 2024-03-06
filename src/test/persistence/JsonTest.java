package persistence;

import model.Book;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkBook(String title, String genre, String author, String form, String audience, Book book) {
        assertEquals(title, book.getTitle() );
        assertEquals(genre, book.getGenre() );
        assertEquals(author, book.getAuthor() );
        assertEquals(form, book.getForm() );
        assertEquals(audience, book.getAudience() );
    }
}
