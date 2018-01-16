package entity;

import java.time.LocalDate;

/**
 *
 */
public class Movie {
    String title;
    String year;
    LocalDate released;
    String genre;
    String director;
    String actors;
    double imdbRating;
    String type;

    public Movie() {
    }

    public Movie(String title, String year, LocalDate released, String genre,
                 String director, String actors, double imdbRating, String type) {
        this.title = title;
        this.year = year;
        this.released = released;
        this.genre = genre;
        this.director = director;
        this.actors = actors;
        this.imdbRating = imdbRating;
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public LocalDate getReleased() {
        return released;
    }

    public void setReleased(LocalDate released) {
        this.released = released;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getActors() {
        return actors;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }

    public double getImdbRating() {
        return imdbRating;
    }

    public void setImdbRating(double imdbRating) {
        this.imdbRating = imdbRating;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "title='" + title + '\'' +
                ", year='" + year + '\'' +
                ", released=" + released +
                ", genre='" + genre + '\'' +
                ", director='" + director + '\'' +
                ", actors='" + actors + '\'' +
                ", imdbRating=" + imdbRating +
                ", type='" + type + '\'' +
                '}';
    }
}
