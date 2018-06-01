package login;

import java.sql.Connection;
import java.sql.SQLException;

public class LoginModel {
    private Connection connection;

    public LoginModel(String login, String password) {
        try {
            this.connection = DbConnection.setConnection(login, password);
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean isDatabaseConnected() {
        return this.connection != null;
    }
}
