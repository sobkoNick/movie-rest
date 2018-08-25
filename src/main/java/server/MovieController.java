package server;

import model.entity.Movie;
import model.service.MovieService;
import model.serviceimpl.MovieServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping(value = "/movie", method = RequestMethod.POST)
    public ResponseEntity<Movie> addMovie(@RequestBody Movie movie) {
        MovieService movieService = new MovieServiceImpl();
        movieService.addMovie(movie);

        Movie movieFromDB = movieService.getMovieByTitle(movie.getTitle());
        if (movieFromDB.getTitle() == null) {
            movie = new Movie("Not added", 0.0, 0.0, 0, 0);
            return new ResponseEntity<>(movie, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(movie, HttpStatus.OK);
    }

    @RequestMapping(value = "/movie", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteMovie(@RequestBody Movie movie) {
        MovieService movieService = new MovieServiceImpl();
        Integer rowsDeleted = movieService.deleteMovieByTitle(movie.getTitle());
        if (rowsDeleted == 1) {
            return new ResponseEntity<>(String.format("Movie '%s' was successfully deleted", movie.getTitle()),
                    HttpStatus.OK);
        }
        return new ResponseEntity<>(String.format("Movie '%s' was not deleted", movie.getTitle()), HttpStatus.NOT_FOUND);
    }

//    @RequestMapping(value = "/addtest", method = RequestMethod.POST)
//    public ResponseEntity<Movie> addTest(@RequestBody String movie) {
//        System.out.println("movie = " + movie);
//        return new ResponseEntity<Movie>(new Movie(), HttpStatus.OK);
//    }

    @RequestMapping(value = "/movies", method = RequestMethod.GET)
    public ResponseEntity<List<Movie>> getAllMovies() {
        MovieService movieService = new MovieServiceImpl();
        List<Movie> movies = movieService.getAllMovies();
        return new ResponseEntity<>(movies, HttpStatus.OK);
    }

    @RequestMapping(value = "/rating/{number}", method = RequestMethod.GET)
    public ResponseEntity<List<Movie>> getMoviesByRating(@PathVariable Integer number) {
        MovieService movieService = new MovieServiceImpl();
        List<Movie> movies = movieService.getMoviesWithRating(number);
        return new ResponseEntity<>(movies, HttpStatus.OK);
    }

    @RequestMapping(value = "/good", method = RequestMethod.GET)
    public ResponseEntity<List<Movie>> getGoodMoview() {
        MovieService movieService = new MovieServiceImpl();
        List<Movie> movies = movieService.getMoviesWithRating(8);
        return new ResponseEntity<>(movies, HttpStatus.OK);
    }
}
