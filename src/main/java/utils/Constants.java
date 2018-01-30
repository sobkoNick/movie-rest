package utils;

/**
 *
 */
public interface Constants {
    String MOVIE_NOT_FOUND = "Movie not found!";

    // imdb constants
    String IMDB_REST_URL = "http://www.omdbapi.com/?t=";
    String API_KEY = "&apikey=ddb21a66";

    // excel constants
    String PATH_TO_EXCEL = "src/main/resources/kinopoisk.data.xlsx";
    Integer TITLE_INDEX = 1;
    Integer MY_RATING_INDEX = 7;
    Integer KINOPOISK_RATING_INDEX = 8;
    Integer BUDGET_INDEX = 15;
    Integer EARNINGS_INDEX = 17;

}
