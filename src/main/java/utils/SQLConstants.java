package utils;

/**
 *
 */
public interface SQLConstants {
    String URL = "jdbc:mysql://localhost/?useUnicode=true&characterEncoding=UTF8";
    String USER = "root";
    String PASSWORD = "123";
    String DROP_DATABASE_IFEXIST = "DROP DATABASE IF EXISTS moviedb;";
    String CREATE_DATABASE = "CREATE DATABASE moviedb default Character set utf8;";
    String USE_DATABASE = "use moviedb;";
    String CREATE_TABLE_MOVIE = "CREATE TABLE movie (\n" +
            "\tid int PRIMARY KEY AUTO_INCREMENT,\n" +
            "    title varchar(40) not null,\n" +
            "    myRating DOUBLE,\n" +
            "    year varchar(20),\n" +
            "    genre varchar(40),\n" +
            "    director varchar(40),\n" +
            "    actors varchar(60),\n" +
            "    imdbRating DOUBLE,\n" +
            "    kinopoiskRating DOUBLE,\n" +
            "    budget INT,\n" +
            "    earnings INT,\n" +
            "    type varchar(10)\n" +
            ") default Character set utf8;";
}
