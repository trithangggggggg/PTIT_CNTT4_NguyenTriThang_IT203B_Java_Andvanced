package session13.bai4;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseManager {

    public static Connection getConnection() throws SQLException {
        String url = "jdbc:sqlserver://localhost:1433;databaseName=demo_hopital;encrypt=false";
        String user = "root";
        String password = "123456";

        return DriverManager.getConnection(url, user, password);
    }
}