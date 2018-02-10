package server;

import model.dao.MovieDao;
import model.entity.Movie;
import model.service.MovieService;
import model.serviceimpl.MovieServiceImpl;
import org.apache.log4j.Logger;
import utils.ExcelParser;
import utils.IMDBRequestMaker;
import utils.SettingUpDB;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 *
 */
public class SettingUpServerData {
    private static final Logger LOGGER = Logger.getLogger(SettingUpServerData.class);
    public void setServerDataFromExcelAndImdb(int moviesToRead) {
        LOGGER.info("Setting up data to server. movies count = " + moviesToRead);

        SettingUpDB.setUp();

        ExcelParser excelParser = new ExcelParser();
        List<Movie> movies = new LinkedList<>();
        List<Movie> moviesAddToDB = new LinkedList<>();

        try {
            movies = excelParser.readMoviesFromXLSXFile(moviesToRead);
        } catch (IOException e) {
            LOGGER.error(e);
        }

        IMDBRequestMaker imdbRequestMaker = new IMDBRequestMaker();
        movies.forEach( movie -> {
            Movie newMovie = mergeMovie(imdbRequestMaker, movie);
            moviesAddToDB.add(newMovie);
        });

        MovieService movieService = new MovieServiceImpl();
        movies.forEach(movieService::addMovie);
    }

    private Movie mergeMovie(IMDBRequestMaker imdbRequestMaker, Movie movie) {
        Movie newMovie = movie;
        Movie movieFromImdb = imdbRequestMaker.getMovieFromIMDB(movie.getTitle());
        newMovie.setYear(movieFromImdb.getYear());
        newMovie.setReleased(movieFromImdb.getReleased());
        newMovie.setGenre(movieFromImdb.getGenre());
        newMovie.setDirector(movieFromImdb.getDirector());
        newMovie.setActors(movieFromImdb.getActors());
        newMovie.setImdbRating(movieFromImdb.getImdbRating());
        newMovie.setType(movieFromImdb.getType());
        return newMovie;
    }
}
