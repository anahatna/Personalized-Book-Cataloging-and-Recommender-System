package model;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static model.Genre.FANTASY_SCIFI;
import static model.Genre.NON_FICTION;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class ListOfBooksReadTest {
    private ListOfBooksRead testListOfBooksRead;
    private Book testBookOne;
    private Book testBookTwo;
    private ArrayList<Book> testBooksRead;

    @BeforeEach
    void runBefore() {
        testListOfBooksRead = new ListOfBooksRead();
    }

    @Test
    void testConstructor() {
        assertTrue(testListOfBooksRead.isEmpty());
        assertEquals(0, testListOfBooksRead.length());
    }

    @Test
    void testAddBook() {
        testBookOne = new Book("Throne of Glass","Sarah J Maas",
                5, FANTASY_SCIFI, "My favourite book ever!!");
        testBookTwo = new Book("A Brief History of Time","Stephen Hawking",
                2, NON_FICTION, "Not very interesting.");
        testBooksRead = new ArrayList<>();

        testListOfBooksRead.addBook(testBookOne);
        testBooksRead.add(testBookOne);
        assertEquals(testBooksRead, testListOfBooksRead.getBooksRead());
        assertEquals(1, testListOfBooksRead.length());
        assertEquals(testBookOne, testListOfBooksRead.getRecentRead());
        assertFalse(testListOfBooksRead.isEmpty());


        testListOfBooksRead.addBook(testBookTwo);
        testBooksRead.add(testBookTwo);
        assertEquals(testBooksRead, testListOfBooksRead.getBooksRead());
        assertEquals(2, testListOfBooksRead.length());
        assertEquals(testBookTwo, testListOfBooksRead.getRecentRead());

    }

    @Test
    void testRemoveBook() {
        testBookOne = new Book("Throne of Glass","Sarah J Maas",
                5, FANTASY_SCIFI, "My favourite book ever!!");
        testBookTwo = new Book("A Brief History of Time","Stephen Hawking",
                2, NON_FICTION, "Not very interesting.");
        testBooksRead = new ArrayList<>();

        testListOfBooksRead.addBook(testBookOne);
        testBooksRead.add(testBookOne);
        assertEquals(testBooksRead, testListOfBooksRead.getBooksRead());
        assertEquals(1, testListOfBooksRead.length());
        assertEquals(testBookOne, testListOfBooksRead.getRecentRead());
        assertFalse(testListOfBooksRead.isEmpty());

        testListOfBooksRead.removeBook(testBookOne);
        testBooksRead.remove(testBookOne);
        assertEquals(0, testListOfBooksRead.length());
        assertTrue(testListOfBooksRead.isEmpty());


        testListOfBooksRead.addBook(testBookOne);
        testListOfBooksRead.addBook(testBookTwo);
        testBooksRead.add(testBookOne);
        testBooksRead.add(testBookTwo);
        assertEquals(testBooksRead, testListOfBooksRead.getBooksRead());
        assertEquals(2, testListOfBooksRead.length());
        testListOfBooksRead.removeBook(testBookOne);
        testBooksRead.remove(testBookOne);
        assertEquals(1, testListOfBooksRead.length());
        assertEquals(testBookTwo, testListOfBooksRead.getRecentRead());

    }


}
