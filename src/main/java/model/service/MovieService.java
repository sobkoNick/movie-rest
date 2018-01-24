package model.service;

import model.entity.Movie;

import java.lang.reflect.Modifier;
import java.util.List;

/**
 *
 */
public interface MovieService {
    public boolean addMovie(Movie movie);
    public List<Movie> getAllMovies();
    public Movie getMovieByTitle(String title);
    public Integer getTotalRowsCountFromDB();
}
