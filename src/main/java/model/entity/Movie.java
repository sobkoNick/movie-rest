package model.entity;

/**
 *
 */

public class Movie {
    private String title;
    private Double myRating;
    private String year;
    private String genre;
    private String director;
    private String actors;
    private Double imdbRating;
    private String type;
    private Double kinopoiskRating;
    private Integer budget;
    private Integer earnings;

    private final String NO_INFO = "No info";

    public Movie() {
        this.myRating = 0.0;
        this.year = NO_INFO;
        this.genre = NO_INFO;
        this.director = NO_INFO;
        this.actors = NO_INFO;
        this.imdbRating = 0.0;
        this.type = NO_INFO;
        this.kinopoiskRating = 0.0;
        this.budget = 0;
        this.earnings = 0;
    }

    public Movie(String title, String year, String genre,
                 String director, String actors, double imdbRating, String type) {
        this.title = title;
        this.year = year;
        this.genre = genre;
        this.director = director;
        this.actors = actors;
        this.imdbRating = imdbRating;
        this.type = type;
    }

    public Movie(String title, Double myRating, String year,
                 String genre, String director, String actors, Double imdbRating,
                 String type, Double kinopoiskRating, Integer budget, Integer earnings) {
        this.title = title;
        this.myRating = myRating;
        this.year = year;
        this.genre = genre;
        this.director = director;
        this.actors = actors;
        this.imdbRating = imdbRating;
        this.type = type;
        this.kinopoiskRating = kinopoiskRating;
        this.budget = budget;
        this.earnings = earnings;

        this.myRating = 0.0;
        this.kinopoiskRating = 0.0;
        this.budget = 0;
        this.earnings = 0;
    }

    public Movie(String title, Double myRating, Double kinopoiskRating, Integer budget, Integer earnings) {
        this.title = title;
        this.myRating = myRating;
        this.kinopoiskRating = kinopoiskRating;
        this.budget = budget;
        this.earnings = earnings;

        this.year = NO_INFO;
        this.genre = NO_INFO;
        this.director = NO_INFO;
        this.actors = NO_INFO;
        this.imdbRating = 0.0;
        this.type = NO_INFO;
    }


    public Double getMyRating() {
        return myRating;
    }

    public void setMyRating(Double myRating) {
        this.myRating = myRating;
    }

    public void setImdbRating(Double imdbRating) {
        this.imdbRating = imdbRating;
    }

    public Double getKinopoiskRating() {
        return kinopoiskRating;
    }

    public void setKinopoiskRating(Double kinopoiskRating) {
        this.kinopoiskRating = kinopoiskRating;
    }

    public Integer getBudget() {
        return budget;
    }

    public void setBudget(Integer budget) {
        this.budget = budget;
    }

    public Integer getEarnings() {
        return earnings;
    }

    public void setEarnings(Integer earnings) {
        this.earnings = earnings;
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
                ", myRating=" + myRating +
                ", year='" + year + '\'' +
                ", genre='" + genre + '\'' +
                ", director='" + director + '\'' +
                ", actors='" + actors + '\'' +
                ", imdbRating=" + imdbRating +
                ", type='" + type + '\'' +
                ", kinopoiskRating=" + kinopoiskRating +
                ", budget=" + budget +
                ", earnings=" + earnings +
                ", NO_INFO='" + NO_INFO + '\'' +
                '}';
    }
}
