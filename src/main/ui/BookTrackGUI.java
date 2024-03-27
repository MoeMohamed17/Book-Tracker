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


    public BookTrackGUI() {
        super("Book Tracking Application");
        this.bookList = new BookList();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Exits out of application
        this.setSize(1000, 600); // Set the size of the window
        this.setLocationRelativeTo(null); // Center the window
        this.setResizable(false); //cant change the size
        this.getContentPane().setBackground(new Color(120, 149, 167)); //change background colour
        this.setVisible(true); //make window visible
        setLayout(new BorderLayout()); // layout manager is BorderLayout
        welcomeLabel = new JLabel("Welcome to BookTrack!", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Serif", Font.BOLD, 40));
        add(welcomeLabel, BorderLayout.NORTH);
        ImageIcon originalIcon = new ImageIcon(getClass().getResource("booklogo2.png"));
        int width = 400;
        int height = 400;
        Image scaledImage = originalIcon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        ImageIcon bookIcon = new ImageIcon(scaledImage);
        imageLabel = new JLabel(bookIcon);
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(imageLabel, BorderLayout.CENTER);


        // Initializing the table
        String[] columnNames = {"Title", "Genre", "Author", "Form", "Audience"};
        tableModel = new DefaultTableModel(columnNames, 0);
        bookTable = new JTable(tableModel);
        scrollPane = new JScrollPane(bookTable);


        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout()); //if more stuff are added, it will go to new line
        this.add(panel, BorderLayout.SOUTH); // Add the panel to the frame

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

        // Button for view unread books
        JButton viewBooksButton = new JButton("View Unread Books");
        panel.add(viewBooksButton);

        // Action Listener for View Book Button
        viewBooksButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayBooksTable();
            }
        });

        // Button for view read books
        JButton viewReadBooksBooksButton = new JButton("View Read Books");
        panel.add(viewReadBooksBooksButton);

        // Action Listener for View Book Button
        viewReadBooksBooksButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayReadBooksTable();
            }
        });


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
                    updateTableModel();
                } else {
                    JOptionPane.showMessageDialog(null, "Please select a book to mark as read.");
                }
            }
        });

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
        JDialog addBookDialog = new JDialog(this, "Add New Book", true);
        addBookDialog.setLayout(new FlowLayout());
        addBookDialog.setLocationRelativeTo(null);
        addBookDialog.setSize(275, 350);
        addBookDialog.setResizable(false); //cant change the size

        // Form fields
        JTextField titleField = new JTextField(20);
        JTextField genreField = new JTextField(20);
        JTextField authorField = new JTextField(20);
        JTextField formField = new JTextField(20);
        JTextField audienceField = new JTextField(20);


        // Labels
        addBookDialog.add(new JLabel("Title:"));
        addBookDialog.add(titleField);
        addBookDialog.add(new JLabel("Genre:"));
        addBookDialog.add(genreField);
        addBookDialog.add(new JLabel("Author:"));
        addBookDialog.add(authorField);
        addBookDialog.add(new JLabel("Literary Form:"));
        addBookDialog.add(formField);
        addBookDialog.add(new JLabel("Audience:"));
        addBookDialog.add(audienceField);


        // Add Save Book button
        JButton saveButton = new JButton("Save Book");
        addBookDialog.add(saveButton);

        // Action Listener for Save Button
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Create and add book to bookList here
                String title = titleField.getText();
                String genre = genreField.getText();
                String author = authorField.getText();
                String form = formField.getText();
                String audience = audienceField.getText();


                Book newBook = new Book(title, genre, author, form, audience);
                bookList.addBook(newBook);
                updateTableModel(); // Call to update the table model
                addBookDialog.dispose(); // Close dialog after saving
            }
        });

        addBookDialog.setVisible(true);
    }

    private void updateTableModel() {
        // Clear the existing table
        tableModel.setRowCount(0);
        ArrayList<Book> books = bookList.unReadBooks();
        if (books != null && !books.isEmpty()) {
            for (Book book : books) {
                tableModel.addRow(new Object[]{book.getTitle(), book.getGenre(), book.getAuthor(), book.getForm(), book.getAudience()});
            }
        } else {
            JOptionPane.showMessageDialog(null, "No Unread books to display.");
        }
    }

    private void displayBooksTable() {
        welcomeLabel.setVisible(false); // Hide the welcome message
        imageLabel.setVisible(false); // Hide the image when displaying books
        add(scrollPane, BorderLayout.CENTER);

        // Refresh the frame to show the table
        validate();
        repaint();
    }

    private void displayReadBooksTable() {
        welcomeLabel.setVisible(false); // Hide the welcome message
        imageLabel.setVisible(false); // Hide the image when displaying read books
        updateReadBooksTableModel(); // Call to update the table model with read books
        add(scrollPane, BorderLayout.CENTER);

        // Refresh the frame to show the table
        validate();
        repaint();
    }

    private void updateReadBooksTableModel() {
        tableModel.setRowCount(0);
        ArrayList<Book> books = bookList.readBooks();

        if (books != null && !books.isEmpty()) {
            for (Book book : books) {
                tableModel.addRow(new Object[]{book.getTitle(), book.getGenre(), book.getAuthor(), book.getForm(), book.getAudience()});
            }
        } else {
            JOptionPane.showMessageDialog(null, "No read books to display.");
        }
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
            updateTableModel();
            JOptionPane.showMessageDialog(null, "Loaded BookList!");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Unable to read to file");
        }
    }

}

