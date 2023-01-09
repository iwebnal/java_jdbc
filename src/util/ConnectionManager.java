package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class ConnectionManager {
    private ConnectionManager() {
    }
    /*
     * url = "jdbc:postgresql://localhost:5432/postgres"
     * username = "postgres";
     * password = "1234";
     * */

//    private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
//    private static final String USERNAME = "postgres";
//    private static final String PASSWORD = "1234";
    private static final String URL_KEY = "db.url";
    private static final String USERNAME_KEY = "db.username";
    private static final String PASSWORD_KEY = "db.password";

    static {
        // for old java version
        loadDriver();
    }

    private static void loadDriver() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static Connection open() {
        try {
//            return DriverManager.getConnection(URL, USERNAME, PASSWORD);
            return DriverManager.getConnection(
                    PropertiesUtil.get(URL_KEY),
                    PropertiesUtil.get(USERNAME_KEY),
                    PropertiesUtil.get(PASSWORD_KEY)
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
