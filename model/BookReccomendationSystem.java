package model;

public class BookReccomendationSystem {
    private ListOfBooksRead booksRead;
    private APIRecs apiRecs;

    public void BookRecommendationSystem(ListOfBooksRead booksRead) {
        this.booksRead = booksRead;
        this.apiRecs = new APIRecs();
    }

    public Book[] getRecommendations() {
        Genre genre = booksRead.getHighestRatedGenre();

        // Convert the genre enum to a string
        String genreString = genre.name(); // or genre.toString();

        return fetchBooksByGenre(genreString);
    }

    private Book[] fetchBooksByGenre(String genre) {
        return APIRecs.fetchBooksByGenre(genre);
    }
}
