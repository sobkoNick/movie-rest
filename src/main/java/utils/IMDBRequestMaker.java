package utils;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import model.entity.Movie;
import org.apache.log4j.Logger;
import org.testng.Assert;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class IMDBRequestMaker implements Constants {
    private static final Logger LOGGER = Logger.getLogger(IMDBRequestMaker.class);

    public Movie getMovieFromIMDB(String title) {
        title = title.replace(" ","_");
        Movie movie = getResultFromJson(IMDB_REST_URL + title + API_KEY);
        return movie;
    }

    private Movie getResultFromJson(String requestUrl) {
        URL url = null;
        try {
            url = new URL(requestUrl);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        HttpURLConnection request = null;
        try {
            request = (HttpURLConnection) url.openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            request.connect();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // Convert to a JSON object to print data
        JsonParser jp = new JsonParser(); //from gson
        JsonElement root = null; //Convert the input stream to a json element
        try {
            root = jp.parse(new InputStreamReader((InputStream) request.getContent()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        JsonObject rootObj = root.getAsJsonObject(); //May be an array, may be an object.
        LOGGER.info(rootObj);
        return getMovie(rootObj);
    }

    private Movie getMovie(JsonObject rootObj) {
        Movie movie = new Movie();
        if (rootObj.has("Error")) {
            movie.setTitle(MOVIE_NOT_FOUND);
        } else {
            movie.setTitle(rootObj.get("Title").getAsString());
            movie.setYear(rootObj.get("Year").getAsString());
            String date = rootObj.get("Released").getAsString();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy").withLocale(Locale.ENGLISH);
            movie.setGenre(rootObj.get("Genre").getAsString());
            movie.setDirector(rootObj.get("Director").getAsString());
            movie.setActors(rootObj.get("Actors").getAsString());
            movie.setImdbRating(rootObj.get("imdbRating").getAsDouble());
            movie.setType(rootObj.get("Type").getAsString());

            movie.setEarnings(0);
            movie.setBudget(0);
            movie.setMyRating(0.0);
            movie.setKinopoiskRating(0.0);
        }
        return movie;
    }
}
