package teststeps;

import model.entity.Movie;
import org.testng.Assert;
import utils.FilmConstants;
import utils.IMDBRequestMaker;

/**
 *
 */
public class ImdbSteps {
    private final String imdbRestUrl = "http://www.omdbapi.com/?t=";
    private final String API_KEY = "&apikey=ddb21a66";
    IMDBRequestMaker imdbRequestMaker;

    public ImdbSteps() {
        imdbRequestMaker = new IMDBRequestMaker();
    }

    public void verifyFilmPresenceInImdb(String title, boolean expected) {
        Movie movie = getMovieFromIMDB(title);
        Assert.assertEquals(!movie.getTitle().contains(FilmConstants.MOVIE_NOT_FOUND), expected);
    }

    public void verifyFilmFromImdb(String title, String expectedDirector) {
        Movie movie = getMovieFromIMDB(title);
        Assert.assertTrue(movie.getDirector().contains(expectedDirector), "This movie wasnt directed by" + expectedDirector);
    }

    public Movie getMovieFromIMDB(String title) {
        Movie movie = imdbRequestMaker.getResultFromJson(imdbRestUrl + title + API_KEY);
        Assert.assertTrue(!movie.getTitle().contains(FilmConstants.MOVIE_NOT_FOUND), "No such movie was found");
        return movie;
    }
}
