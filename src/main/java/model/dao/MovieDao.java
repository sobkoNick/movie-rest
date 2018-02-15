package model.dao;

import org.apache.log4j.Logger;
import utils.SQLConstants;
import utils.SettingUpDB;
import model.entity.Movie;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class MovieDao implements SQLConstants {
    private static final Logger LOGGER = Logger.getLogger(MovieDao.class);
        public void addMovie(Movie movie) {
            LOGGER.info("Add movie to data base = " + movie);
            try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
                SettingUpDB.useMovieDB(connection);
                PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO movie(title, year, released, genre, " +
                        "director, actors, imdbRating, type, myRating, kinopoiskRating, budget, earnings) VALUES " +
                        "(?,?,?,?,?,?,?,?,?,?,?,?)");
                preparedStatement.setString(1, movie.getTitle().length() >= 40 ? movie.getTitle().substring(0, 35) : movie.getTitle());
                preparedStatement.setString(2, movie.getYear());
                preparedStatement.setDate(3, Date.valueOf(movie.getReleased()));
                preparedStatement.setString(4, movie.getGenre());
                preparedStatement.setString(5, movie.getDirector());

                String actors = movie.getActors();
                if (actors.length() > 58) {
                    actors = actors.substring(0, 58);
                }
                preparedStatement.setString(6, actors);

                preparedStatement.setDouble(7, movie.getImdbRating());
                preparedStatement.setString(8, movie.getType());
                preparedStatement.setDouble(9, movie.getMyRating());
                preparedStatement.setDouble(10, movie.getKinopoiskRating());
                preparedStatement.setInt(11, movie.getBudget());
                preparedStatement.setInt(12, movie.getEarnings());
                preparedStatement.execute();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        public List<Movie> getAllMovies() throws SQLException {
            LOGGER.info("Get all movies");
            try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
                SettingUpDB.useMovieDB(connection);
                PreparedStatement preparedStatement = connection.prepareStatement("SELECT * from movie;");
                ResultSet resultSet = preparedStatement.executeQuery();
                List<Movie> movies = new ArrayList<>();
                while (resultSet.next()) {
                    movies.add(getMovieData(resultSet));
                }
                return movies;
            }
        }

        public List<Movie> getMoviesWithRating(int rating) {
            LOGGER.info("Get good movies in MovieDao");
            try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
                SettingUpDB.useMovieDB(connection);
                PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM movie WHERE imdbRating > ?;");
                preparedStatement.setInt(1, rating);
                ResultSet resultSet = preparedStatement.executeQuery();
                List<Movie> movies = new ArrayList<>();
                while (resultSet.next()) {
                    movies.add(getMovieData(resultSet));
                }
                return movies;
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return new ArrayList<>();
        }

        public Movie getMovieByName(String title) throws SQLException {
            LOGGER.info("Get movie by title " + title);
            try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
                SettingUpDB.useMovieDB(connection);
                PreparedStatement preparedStatement = connection.prepareStatement("SELECT  * from movie WHERE title = ?");
                preparedStatement.setString(1, title);
                ResultSet resultSet = preparedStatement.executeQuery();
                Movie movie = new Movie();
                while (resultSet.next()) {
                    movie = getMovieData(resultSet);
                }
                return movie;
            }
        }

        public Integer getTotalRowsCount() throws SQLException {
            LOGGER.info("Get total rows count in db");
            try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)){
                SettingUpDB.useMovieDB(connection);
                PreparedStatement preparedStatement = connection.prepareStatement("SELECT COUNT(*) FROM movie;");
                ResultSet resultSet = preparedStatement.executeQuery();
                Integer totalCount = 0;
                while (resultSet.next()) {
                    totalCount = resultSet.getInt(1);
                }
                return totalCount;
            }
        }

        private Movie getMovieData(ResultSet resultSet) throws SQLException {
            Movie movie = new Movie();
            movie.setTitle(resultSet.getString("title"));
            movie.setMyRating(resultSet.getDouble("myRating"));
            movie.setYear(resultSet.getString("year"));
            movie.setReleased(resultSet.getDate("released").toLocalDate());
            movie.setGenre(resultSet.getString("genre"));
            movie.setDirector(resultSet.getString("director"));
            movie.setActors(resultSet.getString("actors"));
            movie.setImdbRating(resultSet.getDouble("imdbRating"));
            movie.setKinopoiskRating(resultSet.getDouble("kinopoiskRating"));
            movie.setBudget(resultSet.getInt("budget"));
            movie.setEarnings(resultSet.getInt("earnings"));
            movie.setType(resultSet.getString("type"));
            return movie;
        }
}
