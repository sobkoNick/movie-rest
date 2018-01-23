import teststeps.DataBaseSteps;
import teststeps.ImdbSteps;
import utils.ExcelParser;
import utils.IMDBRequestMaker;
import utils.SettingUpDB;
import model.entity.Movie;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import model.serviceimpl.MovieServiceImpl;

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
    public void gettingDunkirkDataFromIMDB_ShouldPass() throws ClassNotFoundException, IOException {
        SettingUpDB.setUp();
        LOGGER.info("Test imdb for Dunkirk film");
        ImdbSteps imdbSteps = new ImdbSteps();
        Movie movie = imdbSteps.getMovieFromIMDB("Dunkirk");
        DataBaseSteps dataBaseSteps = new DataBaseSteps();
        dataBaseSteps.verifyAddingFilmToDB(movie);

        List<Movie> movies = ExcelParser.readMessagesFromXLSXFile("src/main/resources/kinopoisk.data.xlsx", 50);
        System.out.println("movies = " + movies);

        movies.forEach(dataBaseSteps::verifyAddingFilmToDB);
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
