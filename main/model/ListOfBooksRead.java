package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;

// represents a list of books
public class ListOfBooksRead implements Writable {
    private ArrayList<Book> booksRead;
    private int lastIndex;
    private Book lastBookRead;

    // EFFECTS: creates an empty list of Books
    public ListOfBooksRead() {
        this.booksRead = new ArrayList<>();
    }


    // MODIFIES: this
    // EFFECTS: add the given book to the end of booksRead
    public void addBook(Book book) {
        booksRead.add(book);
        EventLog.getInstance().logEvent(new Event("Book was added to the library"));
    }

    // MODIFIES: this
    // EFFECTS: removes the given book to the end of booksRead
    public void removeBook(Book book) {
        booksRead.remove(book);
        EventLog.getInstance().logEvent(new Event("Book was removed from the library"));
    }

    // EFFECTS: returns the current booksRead
    public ArrayList<Book> getBooksRead() {
        return this.booksRead;
    }

    // REQUIRES: non-empty list of books read
    // MODIFIES: this
    // EFFECTS: returns the book that was most recently added into booksRead
    public Book getRecentRead() {
        lastIndex = booksRead.size() - 1;
        lastBookRead = booksRead.get(lastIndex);
        return lastBookRead;
    }

    // EFFECTS: returns the number of books currently in the booksRead list
    public Integer length() {
        return booksRead.size();
    }

    //  EFFECTS: returns true if booksRead list is empty, otherwise returns false
    public boolean isEmpty() {
        if (booksRead.size() == 0) {
            return true;
        }
        return false;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("booksRead", booksReadToJson());
        return json;
    }

    // EFFECTS: returns things in this workroom as a JSON array
    private JSONArray booksReadToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Book b : booksRead) {
            jsonArray.put(b.toJson());
        }

        return jsonArray;
    }
}
