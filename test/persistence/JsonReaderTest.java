package persistence;

import model.Book;
import model.Genre;
import model.ListOfBooksRead;
import org.junit.jupiter.api.Test;
import persistence.JsonReader;
import persistence.JsonTest;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            ListOfBooksRead booksRead = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyWorkRoom.json");
        try {
            ListOfBooksRead booksRead = reader.read();
            assertEquals(0, booksRead.length());
            List<Book> books = booksRead.getBooksRead();
            assertEquals(0, books.size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralLibrary.json");
        try {
            ListOfBooksRead booksRead = reader.read();
            assertEquals(2, booksRead.length());
            List<Book> books = booksRead.getBooksRead();
            assertEquals(2, books.size());
            checkBook("tog","sjm", 5,"read", Genre.FANTASY_SCIFI, books.get(0));
            checkBook("acotar","sjm", 4,"read", Genre.FANTASY_SCIFI, books.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}

