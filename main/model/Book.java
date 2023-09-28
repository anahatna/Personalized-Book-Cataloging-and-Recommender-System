package model;



import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static model.Genre.*;

// Represents a book having a title, author, rating, and review
public class Book implements Writable {
    private String title;
    private String author;
    private int rating;
    private String review;
    private Genre genre;
    private HashMap<Genre, List<String>> listGenreRecommendations = listOfGenreRecommendations();


    // REQUIRES: bookRating must be between 0-5, whole numbers only
    // EFFECTS: title of book is set to bookTitle, the name of the author is set to bookAuthor,
    //          rating of the book is set to bookRating, the review of the book is set to bookReview
    public Book(String bookTitle, String bookAuthor, int bookRating, Genre genre, String bookReview) {
        this.title = bookTitle;
        this.author = bookAuthor;
        this.rating = bookRating;
        this.review = bookReview;
        this.genre = genre;

    }

    // EFFECTS: the list of sci-fi/fantasy book recommendations
    public List<String> listOfSciFiRecs() {
        List<String> listSciFi = new ArrayList<>();
        listSciFi.add("Throne of Glass by Sarah J Maas");
        listSciFi.add("Six of Crows by Leigh Bardugo");
        listSciFi.add("Caraval by Stephanie Garber");
        listSciFi.add("Dune by Frank Herbert");
        listSciFi.add("1984 by George Orwell");
        return listSciFi;
    }

    // EFFECTS: the list of romance book recommendations
    public List<String> listOfRomanceRecs() {
        List<String> listRomance = new ArrayList<>();
        listRomance.add("Love and Other Words by Christina Lauren");
        listRomance.add("The Spanish Love Deception by Elena Armas");
        listRomance.add("The Hating Game by Sally Thorne");
        listRomance.add("The Love Hypothesis by Ali Hazelwood");
        listRomance.add("Beach Read by Emily Henry");
        return listRomance;

    }

    // EFFECTS: the list of nonfiction book recommendations
    public List<String> listOfNonfictionRecs() {
        List<String> listNonfiction = new ArrayList<>();
        listNonfiction.add("The Diary of a Young Girl by Anne Frank");
        listNonfiction.add("Sapiens: A Brief History of HumanKind by Yuval Noah Harari");
        listNonfiction.add("Becoming by Michelle Obama");
        listNonfiction.add("The Glass Castle by Jannette Walls");
        listNonfiction.add("Tuesdays with Morrie by Mitch Albom");
        return listNonfiction;

    }

    // EFFECTS: the list of classic book recommendations
    public List<String> listOfClassicsRecs() {
        List<String> listClassics = new ArrayList<>();
        listClassics.add("The Great Gatsby by F. Scott Fitzgerald");
        listClassics.add("Animal Farm by George Orwell");
        listClassics.add("The Picture of Dorian Gray by Oscar Wilde");
        listClassics.add("Emma by Jane Austen");
        listClassics.add("Oliver Twist by Charles Dickens");
        return listClassics;

    }

    // EFFECTS: the list of horror/mystery book recommendations
    public List<String> listOfMysteryRecs() {
        List<String> listMystery = new ArrayList<>();
        listMystery.add("Gone Girl by Gillian Flynn");
        listMystery.add("The Girl on the Train by Paula Hawkins");
        listMystery.add("Angels & Demons by Dan Brown");
        listMystery.add("In the Woods by Tana French");
        listMystery.add("One of Us Is Lying by Karen M. McManus");
        return listMystery;

    }

    // EFFECTS: the list of the lists of recommendations for each genre
    public HashMap<Genre, List<String>> listOfGenreRecommendations() {
        listGenreRecommendations = new HashMap<>();
        listGenreRecommendations.put(FANTASY_SCIFI, listOfSciFiRecs());
        listGenreRecommendations.put(ROMANCE, listOfRomanceRecs());
        listGenreRecommendations.put(NON_FICTION, listOfNonfictionRecs());
        listGenreRecommendations.put(CLASSICS, listOfClassicsRecs());
        listGenreRecommendations.put(HORROR_MYSTERY, listOfMysteryRecs());
        return listGenreRecommendations;
    }


    // EFFECTS: if bookRating is >= 3:
    //                  - return true
    //          otherwise false
    public boolean isHighRating(Book book) {
        if (rating >= 3) {
            return true;
        }
        return false;
    }

    // EFFECTS: recommends list of books when isHighRating is true,
    //          otherwise if the book genre is not non-fiction, recommend non-fiction books
    //          else recommend romance books
    public List<String> getRecommendations(Book book) {
        if (isHighRating(book)) {
            return listGenreRecommendations.get(this.genre);
        }
        if (this.genre != NON_FICTION) {
            return listGenreRecommendations.get(NON_FICTION);
        }
        return listGenreRecommendations.get(ROMANCE);
    }


    public String getTitle() {
        return this.title;
    }

    public String getAuthor() {
        return this.author;
    }

    public int getRating() {
        return this.rating;
    }

    public String getReview() {
        return this.review;
    }

    public Genre getGenre() {
        return this.genre;
    }

    // EFFECTS: Creates new JSON book object
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("title", title);
        json.put("author", author);
        json.put("rating", rating);
        json.put("review", review);
        json.put("genre", genre);
        return json;
    }
}