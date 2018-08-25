package model.service;

import model.entity.Movie;

import java.lang.reflect.Modifier;
import java.util.List;

/**
 *
 */
public interface MovieService {
    boolean addMovie(Movie movie);
    List<Movie> getAllMovies();
    Movie getMovieByTitle(String title);
    Integer deleteMovieByTitle(String title);
    Integer getTotalRowsCountFromDB();
    List<Movie> getMoviesWithRating(int rating);
}
