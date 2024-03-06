package persistence;

import model.Book;
import model.BookList;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

// Represents a reader that reads BookList from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads workroom from file and returns it;
    // throws IOException if an error occurs reading data from file
    public BookList read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseBookList(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses workroom from JSON object and returns it
    private BookList parseBookList(JSONObject jsonObject) {
        //String name = jsonObject.getString("name");
        BookList bl = new BookList();
        addBooks(bl, jsonObject);
        return bl;
    }

    // MODIFIES: bl
    // EFFECTS: parses thingies from JSON object and adds them to BookList
    private void addBooks(BookList bl, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("books");
        for (Object json : jsonArray) {
            JSONObject nextBook = (JSONObject) json;
            addBook(bl, nextBook);
        }
    }

    // MODIFIES: bl
    // EFFECTS: parses thingy from JSON object and adds it to BookList
    private void addBook(BookList bl, JSONObject jsonObject) {
        String title = jsonObject.getString("title");
        String genre = jsonObject.getString("genre");
        String author = jsonObject.getString("author");
        String form = jsonObject.getString("form");
        String audience = jsonObject.getString("audience");
        String status = jsonObject.getString("status");
        Book book = new Book(title, genre, author, form, audience);
        book.setStatus(status);
        bl.addBook(book);
    }
}
