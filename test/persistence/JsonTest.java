package persistence;

import model.Book;
import model.Genre;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkBook(String title, String author, int rating, String review, Genre genre, Book book) {
        assertEquals(title, book.getTitle());
        assertEquals(author, book.getAuthor());
        assertEquals(rating, book.getRating());
        assertEquals(review, book.getReview());
        assertEquals(genre, book.getGenre());
    }
}

