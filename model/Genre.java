package model;


// represents constant variables as genres
public enum Genre {
    FANTASY_SCIFI("Fantasy/Science Fiction"),
    ROMANCE("Romance"),
    NON_FICTION("Non-Fiction"),
    CLASSICS("Classics"),
    HORROR_MYSTERY("Horror/Mystery");

    private String displayName;

    Genre(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
