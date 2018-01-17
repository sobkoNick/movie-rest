import controller.SettingUpDB;
import dao.MovieDao;
import entity.Movie;
import org.apache.log4j.Logger;
import org.apache.tools.ant.taskdefs.Local;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import service.MovieService;
import serviceimpl.MovieServiceImpl;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class IMDBClientTest {

    // TODO bdd, rest server/client, alure, report ng,

    // ---- http://www.omdbapi.com/?t=Taxi&apikey=ddb21a66
    // http://www.omdbapi.com/?t=Friends&apikey=ddb21a66
    private static final Logger LOGGER = Logger.getLogger(IMDBClientTest.class);
    private String restBaseUrl;
    private final String API_KEY = "&apikey=ddb21a66";
    IMDBRequestMaker imdbRequestMaker;

    @BeforeTest
    public void setUp() throws ClassNotFoundException {
        String driverName = "com.mysql.jdbc.Driver";
        Class.forName(driverName);
        SettingUpDB.setUp();
        restBaseUrl = "http://www.omdbapi.com/";
        imdbRequestMaker = new IMDBRequestMaker();
    }

    @Test
    public void gettingDunkirkDataFromIMDB_ShouldPass() throws ClassNotFoundException {
        LOGGER.info("Test imdb for Dunkirk film");
        Movie movie = imdbRequestMaker.getResultFromJson(restBaseUrl + "?t=Dunkirk&y=2017&plot=full" + API_KEY);
        MovieServiceImpl movieService = new MovieServiceImpl();
        movieService.addMovie(movie);
        LOGGER.info("received movie = " + movie);

        Assert.assertTrue(movie.getDirector().contains("Nolan"), "Result doesnt contain key word Nolan");
        LOGGER.info("ALL  MOVIES = " + movieService.getAllMovies());
    }

//    public static void main(String[] argv) {
//
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-MMM-yyyy").withLocale(Locale.ENGLISH);
//
//        String date = "16-Aug-2016";
//
//        LocalDate localDate = LocalDate.parse(date, formatter);
//
//        System.out.println(localDate);  //default, print ISO_LOCAL_DATE
//
//        System.out.println(formatter.format(localDate));
//
//    }
}
