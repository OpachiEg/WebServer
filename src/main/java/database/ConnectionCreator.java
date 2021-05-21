package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionCreator {

    private String url = "jdbc:postgresql://localhost:5430/test2";
    private String user = "postgres";
    private String password = "traveller0202";

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url,user,password);
    }

}
