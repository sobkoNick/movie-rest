import model.entity.Movie;
import model.service.MovieService;
import model.serviceimpl.MovieServiceImpl;
import org.apache.log4j.Logger;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import server.Application;
import server.SettingUpServerData;
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
        ExcelParser excelParser = new ExcelParser();
        List<Movie> movies = excelParser.readMoviesFromXLSXFile(10);
        LOGGER.info("movies = " + movies);

        movies.forEach(dataBaseSteps::verifyAddingFilmToDB);

        LOGGER.info("imdbSteps.getMovieFromIMDB(\"Twelve Monkeys\") = " + imdbSteps.getMovieFromIMDB("Twelve Monkeys"));

        dataBaseSteps.verifyTotalMovieCountInDB(11);

        LOGGER.info("ExcelParser.findMovieInExcel(\"Gifted\") = " + excelParser.findMovieInExcel("Gifted"));
    }

    @Test
    public void testingSeverData() {
//        Application.main(new String[]{}); // start server

        SettingUpServerData settingUpServerData = new SettingUpServerData();
        settingUpServerData.setServerDataFromExcelAndImdb(25);
        DataBaseSteps dataBaseSteps = new DataBaseSteps();
        dataBaseSteps.verifyFilmPresenceInDB("Dunkirk");
    }
}
