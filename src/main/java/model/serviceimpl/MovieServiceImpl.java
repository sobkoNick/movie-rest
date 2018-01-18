package model.serviceimpl;

import model.dao.MovieDao;
import model.entity.Movie;
import org.apache.log4j.Logger;
import model.service.MovieService;
import utils.SQLConstants;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class MovieServiceImpl implements MovieService {
    private static final Logger LOGGER = Logger.getLogger(MovieServiceImpl.class);

    @Override
    public boolean addMovie(Movie movie) {
        LOGGER.info("Add movie service");
        MovieDao movieDao = new MovieDao();
        try {
            movieDao.addMovie(movie);
            return true;
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            return false;
        }
    }

    @Override
    public List<Movie> getAllMovies() {
        LOGGER.info("Get all movies service");
        MovieDao movieDao = new MovieDao();
        try {
            return movieDao.getAllMovies();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
        return new ArrayList<>();
    }

    @Override
    public Movie getMovieByTitle(String title) {
        LOGGER.info("Get movie by title service");
        MovieDao movieDao = new MovieDao();
        try {
            return movieDao.getMovieByName(title);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
        return new Movie();
    }
}
