package hotel;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import login.DbConnection;

import java.sql.*;


public class HotelController {
    private Connection connection = DbConnection.getConnection();
    private Statement statement;
    private ResultSet resultSet;

    @FXML
    private TextArea outputArea;

    public HotelController() {}

    @FXML
    public void zarzadzajWynajmami() {
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM Wynajem");

            ResultSetMetaData rsmd = resultSet.getMetaData();
            int columnsNumber = rsmd.getColumnCount();
            String resultStr = "";
            String columnValue;
            while (resultSet.next()) {
                for (int i = 1; i <= columnsNumber; i++) {
                    if (i > 1) resultStr += ",  ";
                    columnValue = resultSet.getString(i);
                    resultStr += columnValue + " " + rsmd.getColumnName(i);
                }
                resultStr += "\n";
            }
            outputArea.setText(resultStr);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
