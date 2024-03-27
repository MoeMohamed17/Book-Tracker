package ui;

import model.BookList;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import model.Book;
import persistence.JsonReader;
import persistence.JsonWriter;

public class BookTrackGUI extends JFrame {
    private BookList bookList;
    private DefaultTableModel tableModel;
    private JScrollPane scrollPane;
    private JTable bookTable;
    private JLabel welcomeLabel;
    private JLabel imageLabel;
    private static final String JSON_STORE = "./data/BookList.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private JPanel panel;
    private JTextField titleField;
    private JTextField genreField;
    private JTextField authorField;
    private JTextField formField;
    private JTextField audienceField;


    public BookTrackGUI() {
        super("Book Tracking Application");
        this.bookList = new BookList();
        initialize();
        setWelcomeLabel();
        setImageLabel();
        setTableModel();
        setPanel();
        setAddBookButton();
        setViewBooksButton();
        setViewReadBooksBooksButton();
        setMarkAsReadButton();
        setSaveBooksButton();
        setLoadBooksButton();
    }

    public void initialize() {
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Exits out of application
        this.setSize(1000, 600); // Set the size of the window
        this.setLocationRelativeTo(null); // Center the window
        this.setResizable(false); //cant change the size
        this.getContentPane().setBackground(new Color(120, 149, 167)); //change background colour
        this.setVisible(true); //make window visible
        setLayout(new BorderLayout()); // layout manager is BorderLayout
    }

    public void setWelcomeLabel() {
        welcomeLabel = new JLabel("Welcome to BookTrack!", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Serif", Font.BOLD, 40));
        add(welcomeLabel, BorderLayout.NORTH);
    }

    public void setImageLabel() {
        ImageIcon originalIcon = new ImageIcon(getClass().getResource("booklogo2.png"));
        int width = 400;
        int height = 400;
        Image scaledImage = originalIcon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        ImageIcon bookIcon = new ImageIcon(scaledImage);
        imageLabel = new JLabel(bookIcon);
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(imageLabel, BorderLayout.CENTER);
    }

    public void setTableModel() {
        String[] columnNames = {"Title", "Genre", "Author", "Form", "Audience"};
        tableModel = new DefaultTableModel(columnNames, 0);
        bookTable = new JTable(tableModel);
        scrollPane = new JScrollPane(bookTable);
    }

    public void setPanel() {
        panel = new JPanel();
        panel.setLayout(new FlowLayout()); //if more stuff are added, it will go to new line
        this.add(panel, BorderLayout.SOUTH); // Add the panel to the frame
    }

    public void setAddBookButton() {
        // Button to add a book
        JButton addBookButton = new JButton("Add Book");
        panel.add(addBookButton);

        // Action Listener for Add Book Button
        addBookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showAddBookDialog();
            }
        });
    }

    public void setViewBooksButton() {
        // Button for view unread books
        JButton viewBooksButton = new JButton("View Unread Books");
        panel.add(viewBooksButton);

        // Action Listener for View Book Button
        viewBooksButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayBooks(false);
            }
        });
    }

    public void setViewReadBooksBooksButton() {
        // Button for view read books
        JButton viewReadBooksBooksButton = new JButton("View Read Books");
        panel.add(viewReadBooksBooksButton);

        // Action Listener for View Book Button
        viewReadBooksBooksButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayBooks(true);
            }
        });
    }

    public void setMarkAsReadButton() {
        JButton markAsReadButton = new JButton("Mark as Read");
        panel.add(markAsReadButton);

        // Action Listener for Mark As Read Button
        markAsReadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = bookTable.getSelectedRow();
                if (selectedRow >= 0) { // Check if a row is selected
                    String title = (String) tableModel.getValueAt(selectedRow, 0);
                    bookList.markBookAsRead(title);
                    updateTableModel(false);
                } else {
                    JOptionPane.showMessageDialog(null, "Please select a book to mark as read.");
                }
            }
        });

    }

    public void setSaveBooksButton() {
        // Button for save
        JButton saveBooksButton = new JButton("Save Books");
        panel.add(saveBooksButton);

        // Action Listener for save
        saveBooksButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveBookList();
            }
        });
    }

    public void setLoadBooksButton() {
        // Button for load
        JButton loadBooksButton = new JButton("Load Books");
        panel.add(loadBooksButton);

        // Action Listener for load
        loadBooksButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadBookList();
            }
        });
    }

    private void showAddBookDialog() {
        JDialog addBookDialog = createAddBookDialog();
        addFormFields(addBookDialog);
        addSaveButton(addBookDialog);
        addBookDialog.setVisible(true);
    }

    private JDialog createAddBookDialog() {
        JDialog dialog = new JDialog(this, "Add New Book", true);
        dialog.setLayout(new FlowLayout());
        dialog.setSize(275, 350);
        dialog.setLocationRelativeTo(null);
        dialog.setResizable(false);
        return dialog;
    }

    private void addFormFields(JDialog dialog) {
        titleField = new JTextField(20);
        genreField = new JTextField(20);
        authorField = new JTextField(20);
        formField = new JTextField(20);
        audienceField = new JTextField(20);

        dialog.add(new JLabel("Title:"));
        dialog.add(titleField);
        dialog.add(new JLabel("Genre:"));
        dialog.add(genreField);
        dialog.add(new JLabel("Author:"));
        dialog.add(authorField);
        dialog.add(new JLabel("Literary Form:"));
        dialog.add(formField);
        dialog.add(new JLabel("Audience:"));
        dialog.add(audienceField);
    }

    private void addSaveButton(JDialog dialog) {
        JButton saveButton = new JButton("Save Book");
        saveButton.addActionListener(e -> saveBookAction(dialog));
        dialog.add(saveButton);
    }

    private void saveBookAction(JDialog dialog) {
        // Extract text from fields here, assume they are class variables or pass them as parameters
        String title = titleField.getText();
        String genre = genreField.getText();
        String author = authorField.getText();
        String form = formField.getText();
        String audience = audienceField.getText();

        Book newBook = new Book(title, genre, author, form, audience);
        bookList.addBook(newBook);
        updateTableModel(false); // Updated to support switching between read and unread books
        dialog.dispose();
    }

    private void updateTableModel(boolean displayReadBooks) {
        tableModel.setRowCount(0); // Clear the existing table
        ArrayList<Book> books;
        if (displayReadBooks) {
            books = bookList.readBooks();
        } else {
            books = bookList.unReadBooks();
        }

        if (books != null && !books.isEmpty()) {
            for (Book book : books) {
                tableModel.addRow(new Object[]{book.getTitle(), book.getGenre(),
                        book.getAuthor(), book.getForm(), book.getAudience()});
            }
        } else {
            JOptionPane.showMessageDialog(null,
                    displayReadBooks ? "No read books to display." : "No unread books to display.");
        }
    }

    // This method can be used to toggle between displaying read and unread books
    private void displayBooks(boolean displayReadBooks) {
        welcomeLabel.setVisible(false); // Hide the welcome message
        imageLabel.setVisible(false); // Hide the image
        updateTableModel(displayReadBooks); // Update the table based on the flag
        if (!scrollPane.isDisplayable()) { // If the scrollPane is not already added
            add(scrollPane, BorderLayout.CENTER);
        }
        revalidate(); // Revalidate and repaint to ensure UI updates correctly
        repaint();
    }

    public void saveBookList() {
        try {
            jsonWriter.open();
            jsonWriter.write(bookList);
            jsonWriter.close();
            JOptionPane.showMessageDialog(null, "Saved BookList!");
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Unable to write to file");
        }
    }

    public void loadBookList() {
        try {
            bookList = jsonReader.read();
            updateTableModel(false);
            JOptionPane.showMessageDialog(null, "Loaded BookList!");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Unable to read to file");
        }
    }

}

