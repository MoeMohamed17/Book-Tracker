package model;

//
public class Book {
    private String title;
    private String genre;
    private String author;
    private String form;
    private String audience;
    private String status;


    public Book(String title, String genre, String author, String form, String audience) {
        this.title = title;
        this.genre = genre;
        this.author = author;
        this.form = form;
        this.audience = audience;
        this.status = "unread";
    }

    //REQUIRES:
    //MODIFIES:
    //EFFECTS: Change status of the book
    public void markAsReread() {
        this.status = "read";
    }

    //REQUIRES:
    //MODIFIES:
    //EFFECTS: Update Book title
    public void updateTitle(String newTitle) {
        this.title = newTitle;
    }

    //REQUIRES:
    //MODIFIES:
    //EFFECTS: Update the genre of the book
    public void updateGenre(String newGenre) {
        this.genre = newGenre;
    }

    //REQUIRES:
    //MODIFIES:
    //EFFECTS: Update the author of the book
    public void updateAuthor(String newAuthor) {
        this.author = newAuthor;
    }

    //REQUIRES:
    //MODIFIES:
    //EFFECTS: Update the literary form of the book
    public void updateForm(String newForm) {
        this.form = newForm;
    }

    //REQUIRES:
    //MODIFIES:
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









}
