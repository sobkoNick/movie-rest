package utils;

import utils.SQLConstants;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 */
public class SettingUpDB implements SQLConstants {
    public static void setUp() { // 1 transaction
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            try {
                connection.setAutoCommit(false);
                setDBStatement(connection, DROP_DATABASE_IFEXIST);
                setDBStatement(connection, CREATE_DATABASE);
                setDBStatement(connection, USE_DATABASE);
                setDBStatement(connection, CREATE_TABLE_MOVIE);

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
        setDBStatement(connection, USE_DATABASE);
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
