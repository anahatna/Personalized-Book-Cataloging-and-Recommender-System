package ui;

import model.*;
import model.Event;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

// creates the bookshelf where you can view the library and add books to it
public class ViewLibrary extends JFrame implements ActionListener {

    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;

    private JList list;
    private DefaultListModel listModel;
    private Book book;

    private static final String JSON_STORE = "./data/booksRead.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    JPanel addPanel;
    JButton save;
    JButton load;
    ImageIcon image;
    JLabel displayField;

    private JTextField userBookTitle;
    private JTextField userBookAuthor;
    private JTextField userBookRating;
    private JTextField userBookReview;

    private JComboBox boxOfGenres;
    private ListOfBooksRead booksRead;

    private JDesktopPane desktop;
    private JInternalFrame controlPanel;

    // EFFECTS: constructor that sets up a frame for the list of books read panel and the add book options panel
    public ViewLibrary() {
        booksRead = new ListOfBooksRead();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);

        desktop = new JDesktopPane();
        desktop.addMouseListener(new DesktopFocusAction());
        controlPanel = new JInternalFrame("Books I've Read:", true,
                false, false, false);
        controlPanel.setLayout(new CardLayout());
        controlPanel.setLocation(400, 0);
        controlPanel.setPreferredSize(new Dimension(400, 560));

        setContentPane(desktop);
        setTitle("My Virtual Bookshelf");
        setSize(WIDTH, HEIGHT);

        viewBooksPanel();
        addAndRemoveBookPanel();

        controlPanel.pack();
        controlPanel.setVisible(true);
        desktop.add(controlPanel);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new CloseWindow());
        centreOnScreen();
        setVisible(true);

    }

    // MODIFIES: this
    // EFFECTS: helper to create a panel to view the list of books read
    private void viewBooksPanel() {
        JPanel viewBooks = new JPanel();
        viewBooks.setLayout(new GridLayout(1,1));
        listModel = new DefaultListModel();

        list = new JList(listModel); //data has type Object[]
        list.setLayout(new FlowLayout());
        list.setVisibleRowCount(50);
        viewBooks.add(list);


        controlPanel.add(viewBooks, BorderLayout.EAST);
    }


    // EFFECTS: helper that sets up the save and load to file buttons and creates the add and remove book button
    private void addAndRemoveBookPanel() {

        addPanel = new JPanel();

        handleAddBook();
        saveLoadOptions();

        JButton addButton = new JButton("Add Book");
        AddBookListener addBookListener = new AddBookListener(addButton);
        addButton.setActionCommand("Add Book");
        addButton.addActionListener(addBookListener);
        addButton.setBounds(10,260,130,30);
        add(addButton);

        JButton removeButton = new JButton("Remove Book");
        RemoveBookListener removeBookListener = new RemoveBookListener(removeButton);
        removeButton.setActionCommand("Remove Book");
        removeButton.addActionListener(removeBookListener);
        removeButton.setBounds(170,260,130,30);
        add(removeButton);

        controlPanel.add(addPanel, BorderLayout.EAST);
        setVisible(true);
    }

    // EFFECTS: helper that creates the save and load to file buttons
    private void saveLoadOptions() {
        save = new JButton("Save To File");
        save.addActionListener(this);
        save.setBounds(10,530,170,35);
        add(save);

        load = new JButton("Load From File");
        load.addActionListener(this);
        load.setBounds(180,530,170,35);
        add(load);

        setVisible(true);
    }

    // Represents the action to be taken when the user wants to remove a book from the library
    class RemoveBookListener implements ActionListener {
        private JButton removeButton;

        public RemoveBookListener(JButton button) {
            this.removeButton = button;
        }

        public void actionPerformed(ActionEvent e) {
            //This method can be called only if
            //there's a valid selection
            //so go ahead and remove whatever's selected.
            int index = list.getSelectedIndex();
            listModel.remove(index);
            booksRead.removeBook(booksRead.getRecentRead());


            int size = listModel.getSize();

            if (size == 0) { //Nobody's left, disable firing.
                removeButton.setEnabled(false);

            } else { //Select an index.
                if (index == listModel.getSize()) {
                    //removed item in last position
                    index--;
                }

                list.setSelectedIndex(index);
                list.ensureIndexIsVisible(index);
            }
        }
    }

    // Represents the action to be taken when the user wants to add a new book to the library
    class AddBookListener implements ActionListener, DocumentListener {
        private boolean alreadyEnabled = false;
        private JButton addBookButton;

        public AddBookListener(JButton button) {
            this.addBookButton = button;
        }


        // MODIFIES: this
        // EFFECTS: takes the user inputs and creates a book object and adds it to list of books read
        //          also displays an image of a book when book is successfully added to list of books read
        @Override
        public void actionPerformed(ActionEvent e) {
            String title = userBookTitle.getText();
            String author = userBookAuthor.getText();
            int rating = Integer.parseInt(userBookRating.getText());
            Genre genre = (Genre) boxOfGenres.getItemAt(boxOfGenres.getSelectedIndex());
            String review = userBookReview.getText();

            book = new Book(title, author,rating, genre, review);
            booksRead.addBook(book);

            int index = listModel.size() + 1;

            listModel.addElement(toTitleString(book));

            try {
                image = new ImageIcon(getClass().getResource("image1.png"));
                displayField = new JLabel();
                displayField.setIcon(image);
                displayField.setBounds(60,300,300,200);
                add(displayField);
                setVisible(true);
            } catch (Exception exception) {
                System.out.println("Image cannot be found!");
            }

            resetAddBook(index);
        }

        // MODIFIES: this
        // EFFECTS: resets the JText fields to an empty string
        private void resetAddBook(int index) {
            userBookTitle.requestFocusInWindow();
            userBookTitle.setText("");

            userBookAuthor.requestFocusInWindow();
            userBookAuthor.setText("");

            userBookRating.requestFocusInWindow();
            userBookRating.setText("");

            userBookReview.requestFocusInWindow();
            userBookReview.setText("");

            list.setSelectedIndex(index);
            list.ensureIndexIsVisible(index);
        }

        // EFFECTS: creates a string about the name of the book and author for the given book
        public String toTitleString(Book b) {
            return b.getTitle() + " by " + b.getAuthor();
        }

        // Required by DocumentListener
        @Override
        public void insertUpdate(DocumentEvent e) {
            enableButton();
        }

        // Required by DocumentListener
        @Override
        public void removeUpdate(DocumentEvent e) {
            handleEmptyTextField(e);
        }

        // Required by DocumentListener
        @Override
        public void changedUpdate(DocumentEvent e) {
            if (!handleEmptyTextField(e)) {
                enableButton();
            }
        }

        // EFFECTS: enables the addBookButton
        private void enableButton() {
            if (!alreadyEnabled) {
                addBookButton.setEnabled(true);
            }
        }

        // Required by DocumentListener
        private boolean handleEmptyTextField(DocumentEvent e) {
            if (e.getDocument().getLength() <= 0) {
                addBookButton.setEnabled(false);
                alreadyEnabled = false;
                return true;
            }

            return false;
        }
    }

    // EFFECTS: creates the JLabel and JText fields display for user to add books
    private void handleAddBook() {

        handleBookTitle();

        JLabel bookAuthor = new JLabel("Author:");
        bookAuthor.setBounds(10,50,80,25);
        add(bookAuthor);

        userBookAuthor = new JFormattedTextField();
        userBookAuthor.setBounds(100,50,165,25);
        add(userBookAuthor);

        JLabel bookRating = new JLabel("Rating:");
        bookRating.setBounds(10,80,80,25);
        add(bookRating);

        userBookRating = new JFormattedTextField();
        userBookRating.setBounds(100,80,165,25);
        add(userBookRating);

        handleGenre().setBounds(100,110,165,25);;

        JLabel bookReview = new JLabel("Review:");
        bookReview.setBounds(10,140,80,25);
        add(bookReview);

        userBookReview = new JFormattedTextField();
        userBookReview.setBounds(100,140,200,95);
        add(userBookReview);
    }

    // EFFECTS: creates a JLabel and JTextField for the book title
    private void handleBookTitle() {
        JLabel bookTitle = new JLabel("Book Title:");
        bookTitle.setBounds(10,20,80,25);
        add(bookTitle);

        userBookTitle = new JFormattedTextField();
        userBookTitle.setBounds(100,20,165,25);
        add(userBookTitle);
    }

    // EFFECTS: helps create print genre options combo box
    private JComboBox<Genre> handleGenre() {

        JLabel bookGenre = new JLabel("Genre:");
        bookGenre.setBounds(10,110,80,25);
        add(bookGenre);

        boxOfGenres = new JComboBox<Genre>();
        boxOfGenres.addItem(Genre.FANTASY_SCIFI);
        boxOfGenres.addItem(Genre.ROMANCE);
        boxOfGenres.addItem(Genre.NON_FICTION);
        boxOfGenres.addItem(Genre.CLASSICS);
        boxOfGenres.addItem(Genre.HORROR_MYSTERY);
        add(boxOfGenres);

        return boxOfGenres;
    }

    // MODIFIES: this
    // EFFECTS: saves the library to file if save button is clicked, loads workroom from file if load button is clicked
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(save)) {
            try {
                System.out.println("Saved!");
                jsonWriter.open();
                jsonWriter.write(booksRead);
                jsonWriter.close();
            } catch (FileNotFoundException exception) {
                System.out.println("Unable to write to file: " + JSON_STORE);
            }
        }
        if (e.getSource().equals(load)) {
            try {
                booksRead = jsonReader.read();
                System.out.println("Loaded " + booksRead.getBooksRead() + " from " + JSON_STORE);
                for (String book : toBookString()) {
                    listModel.addElement(book);
                }
            } catch (IOException exception) {
                System.out.println("Unable to read from file: " + JSON_STORE);
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: creates a list of string of a book's author and title in the list of books read
    public ArrayList<String> toBookString() {
        ArrayList<String> booksReadStrings = new ArrayList<>();
        for (Book bookInList : booksRead.getBooksRead()) {
            booksReadStrings.add(bookInList.getTitle() + " by " + bookInList.getAuthor());
        }
        return booksReadStrings;
    }


    // Represents action to be taken when user clicks desktop to switch focus (Needed for key handling)
    private class DesktopFocusAction extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {
            ViewLibrary.this.requestFocusInWindow();
        }
    }


    // Helper to centre main application window on desktop
    private void centreOnScreen() {
        int width = Toolkit.getDefaultToolkit().getScreenSize().width;
        int height = Toolkit.getDefaultToolkit().getScreenSize().height;
        setLocation((width - getWidth()) / 2, (height - getHeight()) / 2);
    }

    public class CloseWindow extends WindowAdapter {
        @Override
        public void windowClosing(WindowEvent windowEvent) {
            for (Event next : EventLog.getInstance()) {
                System.out.println(next.toString());
            }
            System.exit(0);
        }
    }

}
