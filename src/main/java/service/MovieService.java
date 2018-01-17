package service;

import entity.Movie;

import java.util.List;

/**
 *
 */
public interface MovieService {
    public boolean addMovie(Movie movie);
    public List<Movie> getAllMovies();
}
