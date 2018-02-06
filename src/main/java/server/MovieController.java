package server;

import model.entity.Movie;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
        return new ResponseEntity<String>(helpString, HttpStatus.OK);
    }

    @RequestMapping(value = "/movie/{title}", method = RequestMethod.GET)
    public ResponseEntity<Movie> getMovie(@PathVariable String title) {
        Movie movie = new Movie(title, 8.0, 9.0, 10000, 20000);
        return new ResponseEntity<Movie>(movie, HttpStatus.OK);
    }
}
