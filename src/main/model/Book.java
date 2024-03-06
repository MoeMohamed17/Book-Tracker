package model;

import org.json.JSONObject;
import persistence.Writable;

//A class that constructs the different characteristics of a book
public class Book implements Writable {
    private String title;
    private String genre;
    private String author;
    private String form;
    private String audience;
    private String status;

    //Constructs a book
    public Book(String title, String genre, String author, String form, String audience) {
        this.title = title;
        this.genre = genre;
        this.author = author;
        this.form = form;
        this.audience = audience;
        this.status = "unread";
    }


    //MODIFIES:this
    //EFFECTS: Change status of the book
    public void markAsReread() {
        this.status = "read";
    }


    //MODIFIES: this
    //EFFECTS: Update Book title
    public void updateTitle(String newTitle) {
        this.title = newTitle;
    }


    //MODIFIES: this
    //EFFECTS: Update the genre of the book
    public void updateGenre(String newGenre) {
        this.genre = newGenre;
    }


    //MODIFIES: this
    //EFFECTS: Update the author of the book
    public void updateAuthor(String newAuthor) {
        this.author = newAuthor;
    }


    //MODIFIES: this
    //EFFECTS: Update the literary form of the book
    public void updateForm(String newForm) {
        this.form = newForm;
    }


    //MODIFIES: this
    //EFFECTS: Update the audience of the book
    public void updateAudience(String newAudience) {
        this.audience = newAudience;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getAudience() {
        return audience;
    }

    public String getGenre() {
        return genre;
    }

    public String getForm() {
        return form;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("title", title);
        json.put("genre", genre);
        json.put("author", author);
        json.put("form", form);
        json.put("audience", audience);
        json.put("status", status);
        return json;
    }
}
