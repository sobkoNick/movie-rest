package server;

import model.dao.MovieDao;
import model.entity.Movie;
import model.service.MovieService;
import model.serviceimpl.MovieServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

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
}
