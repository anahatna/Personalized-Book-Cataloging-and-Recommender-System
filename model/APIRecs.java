package model;


import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class APIRecs {


    private static final String BASE_URL = "https://openlibrary.org/search.json?q=";

    public Book fetchBookData(String query) {
        try {
            String urlString = BASE_URL + query;
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            return parseBookFromJson(response.toString());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private Book parseBookFromJson(String json) {
        JSONObject jsonObject = new JSONObject(json);
        JSONArray docs = jsonObject.getJSONArray("docs");

        if (docs.length() > 0) {
            JSONObject bookJson = docs.getJSONObject(0); // Take the first result
            String title = bookJson.optString("title");
            String author = bookJson.optString("author_name", "Unknown Author");
            int rating = bookJson.optInt("average_rating", 0);
            Genre genre = mapGenre(bookJson.optString("subject", "Unknown Genre"));

            return new Book(title, author, rating, genre, " ");
        }
        return null; // No books found
    }

    private static Genre mapGenre(String genreString) {
        switch (genreString.toLowerCase()) {
            case "fantasy":
            case "science fiction":
                return Genre.FANTASY_SCIFI;
            case "romance":
                return Genre.ROMANCE;
            case "non-fiction":
                return Genre.NON_FICTION;
            case "classics":
                return Genre.CLASSICS;
            case "horror":
            case "mystery":
                return Genre.HORROR_MYSTERY;
            default:
                return null; // Or a default genre
        }
    }

    public static Book[] fetchBooksByGenre(String genre) {
        String query = "subject:" + genre;
        try {
            String urlString = BASE_URL + query;
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            return parseBooksFromJson(response.toString());
        } catch (Exception e) {
            e.printStackTrace();
            return new Book[0]; // Return an empty array if there's an error
        }
    }

    private static Book[] parseBooksFromJson(String json) {
        JSONObject jsonObject = new JSONObject(json);
        JSONArray docs = jsonObject.getJSONArray("docs");
        Book[] books = new Book[docs.length()];

        for (int i = 0; i < docs.length(); i++) {
            JSONObject bookJson = docs.getJSONObject(i);
            String title = bookJson.optString("title");
            String author = bookJson.optString("author_name", "Unknown Author");
            int rating = bookJson.optInt("average_rating", 0);
            Genre genre = mapGenre(bookJson.optString("subject", "Unknown Genre"));

            books[i] = new Book(title, author, rating, genre, "");
        }
        return books;
    }

}
