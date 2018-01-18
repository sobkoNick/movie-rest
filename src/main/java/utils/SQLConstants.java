package utils;

/**
 *
 */
public class SQLConstants {
    public static final String URL = "jdbc:mysql://localhost/?useUnicode=true&characterEncoding=UTF8";
    public static final String USER = "root";
    public static final String PASSWORD = "123";
    public static final String DROP_DATABASE_IFEXIST = "DROP DATABASE IF EXISTS moviedb;";
    public static final String CREATE_DATABASE = "CREATE DATABASE moviedb default Character set utf8;";
    public static final String USE_DATABASE = "use moviedb;";
    public static final String CREATE_TABLE_MOVIE = "CREATE TABLE movie (\n" +
            "\tid int PRIMARY KEY AUTO_INCREMENT,\n" +
            "    title varchar(40) not null,\n" +
            "    year varchar(20) not null,\n" +
            "    released DATE,\n" +
            "    genre varchar(40),\n" +
            "    director varchar(40),\n" +
            "    actors varchar(60),\n" +
            "    imdbRating DOUBLE,\n" +
            "    type varchar(10)\n" +
            ") default Character set utf8;";
}
