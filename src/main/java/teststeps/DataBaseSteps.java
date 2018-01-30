package teststeps;

import model.entity.Movie;
import model.service.MovieService;
import model.serviceimpl.MovieServiceImpl;
import org.apache.log4j.Logger;
import org.testng.Assert;

/**
 *
 */
public class DataBaseSteps {
    private static final Logger LOGGER = Logger.getLogger(DataBaseSteps.class);

    public void verifyFilmPresenceInDB(String title) {
        LOGGER.info("verifyFilmPresenceInDB() with title =" + title);
        MovieService movieService = new MovieServiceImpl();
        Movie movie = movieService.getMovieByTitle(title);
        Assert.assertEquals(movie.getTitle(), title, "Titles didnt match");
    }

    public void verifyAddingFilmToDB(Movie movie) {
        LOGGER.info("verifyAddingFilmToDB() with movie" + movie.getTitle());
        MovieService movieService = new MovieServiceImpl();
        movieService.addMovie(movie);
        Assert.assertEquals(movieService.getMovieByTitle(movie.getTitle()).getDirector(),
                movie.getDirector(), "This movie wasnt added to db");
    }

    public void verifyTotalMovieCountInDB(int expectedCount) {
        LOGGER.info("verifyTotalMovieCountInDB() with expected count = " + expectedCount);
        MovieService movieService = new MovieServiceImpl();
        int actualCount = movieService.getTotalRowsCountFromDB();
        Assert.assertEquals(actualCount, expectedCount, "DB has more or less values");
    }
}
