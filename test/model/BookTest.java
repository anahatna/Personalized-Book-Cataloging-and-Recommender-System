package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static model.Genre.*;
import static org.junit.jupiter.api.Assertions.*;

class BookTest {

    private Book testBookOne;
    private Book testBookTwo;
    private Book testBookThree;
    private Book testBookFour;

    @BeforeEach
    void runBefore() {
        testBookOne = new Book("Throne of Glass","Sarah J Maas",
                5, FANTASY_SCIFI, "My favourite book ever!!");

        testBookTwo = new Book("A Brief History of Time","Stephen Hawking",
                2, NON_FICTION, "Not very interesting.");
    }

    @Test
    void testConstructor() {
        assertEquals("Throne of Glass", testBookOne.getTitle());
        assertEquals("Sarah J Maas", testBookOne.getAuthor());
        assertEquals(5, testBookOne.getRating());
        assertEquals(FANTASY_SCIFI, testBookOne.getGenre());
        assertEquals("My favourite book ever!!", testBookOne.getReview());
    }

    @Test
    void testIsHighRating() {
        assertTrue(testBookOne.isHighRating(testBookOne));
        assertFalse(testBookTwo.isHighRating(testBookTwo));
    }

    @Test
    void getRecommendations() {
        testBookThree = new Book("People We Meet On Vacation","Emily Henry",
                0, ROMANCE, "Very predictable ending!");
        testBookFour = new Book("Sharp Objects","Gillian Flynn",
                3, HORROR_MYSTERY, "Wow, not bad.");
        assertEquals(testBookOne.listOfSciFiRecs() ,testBookOne.getRecommendations(testBookOne));
        assertEquals(testBookTwo.listOfRomanceRecs(), testBookTwo.getRecommendations(testBookTwo));
        assertEquals(testBookThree.listOfNonfictionRecs(), testBookThree.getRecommendations(testBookThree));
        assertEquals(testBookFour.listOfMysteryRecs(), testBookFour.getRecommendations(testBookFour));

    }
}