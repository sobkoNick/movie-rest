import model.entity.Movie;
import org.apache.log4j.Logger;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import teststeps.DataBaseSteps;
import teststeps.ImdbSteps;
import utils.ExcelParser;
import utils.SettingUpDB;

import java.io.IOException;
import java.util.List;

public class IMDBClientTest {

    // TODO bdd, rest server/client, alure, report ng, set logger as source for assert

    // ---- http://www.omdbapi.com/?t=Taxi&apikey=ddb21a66
    // http://www.omdbapi.com/?t=Friends&apikey=ddb21a66
    private static final Logger LOGGER = Logger.getLogger(IMDBClientTest.class);


    @BeforeTest
    public void setUp() throws ClassNotFoundException {
        String driverName = "com.mysql.jdbc.Driver";
        Class.forName(driverName);
    }

    @Test
    public void simpleTest_ShouldPass() throws ClassNotFoundException, IOException {
        SettingUpDB.setUp();
        LOGGER.info("Test imdb for Dunkirk film");
        ImdbSteps imdbSteps = new ImdbSteps();
        Movie movie = imdbSteps.getMovieFromIMDB("Dunkirk");
        DataBaseSteps dataBaseSteps = new DataBaseSteps();
        dataBaseSteps.verifyAddingFilmToDB(movie);

        List<Movie> movies = ExcelParser.readMessagesFromXLSXFile(10);
        System.out.println("movies = " + movies);

        movies.forEach(dataBaseSteps::verifyAddingFilmToDB);

        System.out.println("imdbSteps.getMovieFromIMDB(\"Twelve Monkeys\") = " + imdbSteps.getMovieFromIMDB("Twelve Monkeys"));

        dataBaseSteps.verifyTotalMovieCountInDB(11);

        System.out.println("ExcelParser.findMovieInExcel(\"Gifted\") = " + ExcelParser.findMovieInExcel("Gifted"));
    }
}
