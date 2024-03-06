package persistence;

import model.Book;
import model.BookList;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonWriterTest extends JsonTest{

    @Test
    void testWriterInvalidFile() {
        try {
            BookList bl = new BookList();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyWorkroom() {
        try {
            BookList bl = new BookList();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyBookList.json");
            writer.open();
            writer.write(bl);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyBookList.json");
            bl = reader.read();
            assertEquals(0, bl.countBooks());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralWorkroom() {
        try {
            BookList bl = new BookList();
            bl.addBook(new Book("animal farm", "horror", "Moe", "fiction", "adult"));
            bl.addBook(new Book("atomic habits", "motivational", "james", "non-fiction", "adult"));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralBookList.json");
            writer.open();
            writer.write(bl);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralBookList.json");
            bl = reader.read();
            List<Book> books = bl.allBooks();
            assertEquals(2, books.size());
            checkBook("animal farm", "horror", "Moe", "fiction", "adult", books.get(0));
            checkBook("atomic habits", "motivational", "james", "non-fiction", "adult", books.get(1));
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
