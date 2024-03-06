package persistence;

import model.Book;
import model.BookList;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            BookList bl = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyBookList.json");
        try {
            BookList bl = reader.read();
            assertEquals(0, bl.countBooks());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralBookList.json");
        try {
            BookList bl = reader.read();
            List<Book> books = bl.allBooks();
            assertEquals(2, books.size());
            checkBook("animal farm", "horror", "Moe", "fiction", "adult", books.get(0));
            checkBook("atomic habits", "motivational", "james", "non-fiction", "adult", books.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
