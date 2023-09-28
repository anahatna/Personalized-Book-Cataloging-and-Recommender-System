package persistence;

import model.Genre;
import model.Book;
import model.ListOfBooksRead;
import org.junit.jupiter.api.Test;
import persistence.JsonReader;
import persistence.JsonTest;
import persistence.JsonWriter;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JsonWriterTest extends JsonTest {

    @Test
    void testWriterInvalidFile() {
        try {
            ListOfBooksRead booksRead = new ListOfBooksRead();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyWorkroom() {
        try {
            ListOfBooksRead booksRead = new ListOfBooksRead();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyWorkroom.json");
            writer.open();
            writer.write(booksRead);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyWorkroom.json");
            booksRead = reader.read();
            assertEquals(0, booksRead.getBooksRead().size());
            assertEquals(0, booksRead.length());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralWorkroom() {
        try {
            ListOfBooksRead booksRead = new ListOfBooksRead();
            booksRead.addBook(new Book("tog","sjm", 5,
                    Genre.FANTASY_SCIFI, "read"));
            booksRead.addBook(new Book("acotar","sjm", 4,
                    Genre.FANTASY_SCIFI, "read"));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralWorkroom.json");
            writer.open();
            writer.write(booksRead);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralWorkroom.json");
            booksRead = reader.read();
            List<Book> books = booksRead.getBooksRead();
            assertEquals(2, books.size());
            checkBook("tog","sjm", 5,"read", Genre.FANTASY_SCIFI, books.get(0));
            checkBook("acotar","sjm", 4,"read", Genre.FANTASY_SCIFI, books.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
