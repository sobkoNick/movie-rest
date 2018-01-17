package serviceimpl;

import dao.MovieDao;
import entity.Movie;
import org.apache.log4j.Logger;
import service.MovieService;
import sqlConst.SQLConstants;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 *
 */
public class MovieServiceImpl implements MovieService {
    private static final Logger LOGGER = Logger.getLogger(MovieServiceImpl.class);
    private Connection connection;

    @Override
    public boolean addMovie(Movie movie) {
        LOGGER.info("Add movie");
        try (Connection connection = DriverManager.getConnection(SQLConstants.URL, SQLConstants.USER, SQLConstants.PASSWORD)) {
            MovieDao movieDao = new MovieDao();
            movieDao.addMovie(connection, movie);
            LOGGER.info("result set = " + true);
            return true;
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            return false;
        }
    }

    @Override
    public List<Movie> getAllMovies() {
        LOGGER.info("Get all movies");
        try (Connection connection = DriverManager.getConnection(SQLConstants.URL, SQLConstants.USER, SQLConstants.PASSWORD)) {
            MovieDao movieDao = new MovieDao();
            return movieDao.getAllMovies(connection);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            return null;
        }
    }
}
