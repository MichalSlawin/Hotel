package login;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {

    private static Connection connection;

    public static Connection setConnection(String login, String password) throws SQLException {

        String MSSQLCONN = "jdbc:sqlserver://153.19.7.13:1401;user=" + login + ";password=" + password;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection(MSSQLCONN);
            return DriverManager.getConnection(MSSQLCONN);
        } catch(ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Connection getConnection() {
        return connection;
    }
}
