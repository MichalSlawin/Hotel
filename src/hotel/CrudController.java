package hotel;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import login.DbConnection;

import java.sql.*;

public class CrudController {
    String nazwaTabeli;
    private Connection connection = DbConnection.getConnection();
    private Statement statement;
    private ResultSet resultSet;
    @FXML
    private TextArea resultArea;

    @FXML
    public void wypiszTabele() {
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM " + nazwaTabeli);

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
            resultArea.setText(resultStr);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setNazwaTabeli(String nazwaTabeli) {
        this.nazwaTabeli = nazwaTabeli;
    }
}
