package controller;

import sqlConst.SQLConstants;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 */
public class SettingUpDB {
    public static void setUp() { // 1 transaction
        try (Connection connection = DriverManager.getConnection(SQLConstants.URL, SQLConstants.USER, SQLConstants.PASSWORD)) {
            try {
                connection.setAutoCommit(false);
                setDBStatement(connection, SQLConstants.DROP_DATABASE_IFEXIST);
                setDBStatement(connection, SQLConstants.CREATE_DATABASE);
                setDBStatement(connection, SQLConstants.USE_DATABASE);
                setDBStatement(connection, SQLConstants.CREATE_TABLE_MOVIE);

                connection.commit();
            } catch (SQLException e) {
                connection.rollback();
            } finally {
                if (connection != null) {
                    connection.close();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void useMovieDB(Connection connection) throws SQLException {
        setDBStatement(connection, SQLConstants.USE_DATABASE);
    }

    public static void setDBStatement(Connection connection, String sql) throws SQLException {
        Statement setUpDBStatement = null;
        try {
            setUpDBStatement = connection.createStatement();
            setUpDBStatement.execute(sql);
        } finally {
            if (setUpDBStatement != null) {
                setUpDBStatement.close();
            }
        }
    }
}
