package model.dao;

import utils.SQLConstants;
import utils.SettingUpDB;
import model.entity.Movie;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class MovieDao {
        public void addMovie(Movie movie) throws SQLException {
            System.out.println("movie = " + movie);
            try (Connection connection = DriverManager.getConnection(SQLConstants.URL, SQLConstants.USER, SQLConstants.PASSWORD)) {
                SettingUpDB.useMovieDB(connection);
                PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO movie(title, year, released, genre, " +
                        "director, actors, imdbRating, type, myRating, kinopoiskRating, budget, earnings) VALUES " +
                        "(?,?,?,?,?,?,?,?,?,?,?,?)");
                preparedStatement.setString(1, movie.getTitle().length() >= 40 ? movie.getTitle().substring(0, 35) : movie.getTitle());
                preparedStatement.setString(2, movie.getYear());
                preparedStatement.setDate(3, Date.valueOf(movie.getReleased()));
                preparedStatement.setString(4, movie.getGenre());
                preparedStatement.setString(5, movie.getDirector());
                preparedStatement.setString(6, movie.getActors().contains("no info") ? "no info" : movie.getActors().substring(0, 60));
                preparedStatement.setDouble(7, movie.getImdbRating());
                preparedStatement.setString(8, movie.getType());
                preparedStatement.setDouble(9, movie.getMyRating());
                preparedStatement.setDouble(10, movie.getKinopoiskRating());
                preparedStatement.setInt(11, movie.getBudget());
                preparedStatement.setInt(12, movie.getEarnings());
                preparedStatement.execute();
            }
        }

        public List<Movie> getAllMovies() throws SQLException {
            try (Connection connection = DriverManager.getConnection(SQLConstants.URL, SQLConstants.USER, SQLConstants.PASSWORD)) {
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

        public Movie getMovieByName(String title) throws SQLException {
            try (Connection connection = DriverManager.getConnection(SQLConstants.URL, SQLConstants.USER, SQLConstants.PASSWORD)) {
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
            try (Connection connection = DriverManager.getConnection(SQLConstants.URL, SQLConstants.USER, SQLConstants.PASSWORD)){
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
            movie.setYear(resultSet.getString("year"));
            movie.setReleased(resultSet.getDate("released").toLocalDate());
            movie.setGenre(resultSet.getString("genre"));
            movie.setDirector(resultSet.getString("director"));
            movie.setActors(resultSet.getString("actors"));
            movie.setImdbRating(resultSet.getDouble("imdbRating"));
            movie.setType(resultSet.getString("type"));
            return movie;
        }
}
