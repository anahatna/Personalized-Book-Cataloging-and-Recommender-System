package persistence;

import model.Book;
import model.Genre;
import model.ListOfBooksRead;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.*;

// Represents a reader that reads workroom from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads list of books read from file and returns it;
    // throws IOException if an error occurs reading data from file
    public ListOfBooksRead read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseWorkRoom(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses readList from JSON object and returns it
    private ListOfBooksRead parseWorkRoom(JSONObject jsonObject) {
        ListOfBooksRead readList = new ListOfBooksRead();
        addBooks(readList, jsonObject);
        return readList;
    }

    // MODIFIES: readList
    // EFFECTS: parses books from JSON object and adds them to list of books read
    private void addBooks(ListOfBooksRead readList, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("booksRead");
        for (Object json : jsonArray) {
            JSONObject nextBook = (JSONObject) json;
            addBook(readList, nextBook);
        }
    }


    // MODIFIES: readList
    // EFFECTS: parses book from JSON object and adds it to list of books read
    private void addBook(ListOfBooksRead readList, JSONObject jsonObject) {
        String title = jsonObject.getString("title");
        String author = jsonObject.getString("author");
        int rating = jsonObject.getInt("rating");
        String review = jsonObject.getString("review");
        Genre genre = Genre.valueOf(jsonObject.getString("genre"));
        Book book = new Book(title, author, rating, genre, review);
        readList.addBook(book);
    }
}