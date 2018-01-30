package teststeps;

import model.entity.Movie;
import org.testng.Assert;
import utils.Constants;
import utils.IMDBRequestMaker;

/**
 *
 */
public class ImdbSteps implements  Constants{
    IMDBRequestMaker imdbRequestMaker;

    public ImdbSteps() {
        imdbRequestMaker = new IMDBRequestMaker();
    }

    public void verifyFilmPresenceInImdb(String title, boolean expected) {
        Movie movie = getMovieFromIMDB(title);
        Assert.assertEquals(!movie.getTitle().contains(MOVIE_NOT_FOUND), expected);
    }

    public void verifyFilmFromImdb(String title, String expectedDirector) {
        Movie movie = getMovieFromIMDB(title);
        Assert.assertTrue(movie.getDirector().contains(expectedDirector), "This movie wasnt directed by" + expectedDirector);
    }

    public Movie getMovieFromIMDB(String title) {
        title = title.replace(" ","_");
        Movie movie = imdbRequestMaker.getResultFromJson(IMDB_REST_URL + title + API_KEY);
        Assert.assertTrue(!movie.getTitle().contains(Constants.MOVIE_NOT_FOUND), "No such movie was found");
        return movie;
    }
}
