package server;

import model.entity.Movie;
import model.service.MovieService;
import model.serviceimpl.MovieServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 *
 */
@RestController
public class MovieController {
    @RequestMapping(value = "/help", method = RequestMethod.GET)
    public ResponseEntity<String> hello() {
        String helpString = "Hello! Movie REST Service welcomes you.\n" +
                "We have such functianal:\n" +
                "1) /movie/Name - simple method";
        return new ResponseEntity<>(helpString, HttpStatus.OK);
    }

    @RequestMapping(value = "/movie/{title}", method = RequestMethod.GET)
    public ResponseEntity<Movie> getMovie(@PathVariable String title) {
        MovieService movieService = new MovieServiceImpl();
        Movie movie = movieService.getMovieByTitle(title);

        if (movie.getTitle() == null) {
            movie = new Movie("Not found", 0.0, 0.0, 0, 0);
            return new ResponseEntity<>(movie, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(movie, HttpStatus.OK);
    }

    // does not work
    @RequestMapping(value = "/add", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ResponseEntity<Movie> addMovie(Movie movie) {
        MovieService movieService = new MovieServiceImpl();
        movieService.addMovie(movie);

        Movie movieFromDB = movieService.getMovieByTitle(movie.getTitle());
        if (movieFromDB.getTitle() == null) {
            movie = new Movie("Not added", 0.0, 0.0, 0, 0);
            return new ResponseEntity<>(movie, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(movie, HttpStatus.OK);
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ResponseEntity<List<Movie>> getAllMovies() {
        MovieService movieService = new MovieServiceImpl();
        List<Movie> movies = movieService.getAllMovies();
        return new ResponseEntity<>(movies, HttpStatus.OK);
    }
}
