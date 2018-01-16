import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import entity.Movie;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class IMDBRequestMaker {
    private static final Logger LOGGER = Logger.getLogger(IMDBRequestMaker.class);

    public Movie getResultFromJson(String requestUrl) {
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

//        return rootObj.toString();

//        try {
//            number = format.parse(rootObj.get("result").getAsString());
//        } catch (ParseException e) {
//            LOGGER.error(e);
//        }
//        return number.doubleValue();
    }

    private Movie getMovie(JsonObject rootObj) {
        Movie movie = new Movie();
        movie.setTitle(rootObj.get("Title").getAsString());
        movie.setYear(rootObj.get("Year").getAsString());
        String date = rootObj.get("Released").getAsString();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy").withLocale(Locale.ENGLISH);
        movie.setReleased(LocalDate.parse(date, formatter));
        movie.setGenre(rootObj.get("Genre").getAsString());
        movie.setDirector(rootObj.get("Director").getAsString());
        movie.setActors(rootObj.get("Actors").getAsString());
        movie.setImdbRating(rootObj.get("imdbRating").getAsDouble());
        movie.setType(rootObj.get("Type").getAsString());
        return movie;
    }
}
