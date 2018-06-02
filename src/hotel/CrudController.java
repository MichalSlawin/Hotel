package hotel;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import login.DbConnection;

import java.sql.*;

public class CrudController {
    private String nazwaTabeli;
    private Connection connection = DbConnection.getConnection();
    private Statement statement;
    private ResultSet resultSet;
    private String previousID;
    @FXML
    private TextArea resultArea;
    @FXML
    private TextField addField;
    @FXML
    private TextField readField;
    @FXML
    private TextField updateField;
    @FXML
    private TextField statusField;

    @FXML
    public void wypiszTabele() {
        try {
            statement = connection.createStatement();
            if(readField.getText().trim().isEmpty()) {
                resultSet = statement.executeQuery("SELECT * FROM " + nazwaTabeli + ";");
            }
            else {
                resultSet = statement.executeQuery("SELECT * FROM " + nazwaTabeli + " WHERE " + readField.getText() + ";");
            }

            ResultSetMetaData rsmd = resultSet.getMetaData();
            int columnsNumber = rsmd.getColumnCount();
            String resultStr = "";
            String columnValue;
            String columnName;
            for (int i = 1; i <= columnsNumber; i++) {
                columnName = rsmd.getColumnName(i);
                resultStr += columnName;
                if(i != columnsNumber) {
                    resultStr += " | ";
                }
            }
            resultStr += "\n\n";
            while (resultSet.next()) {
                for (int i = 1; i <= columnsNumber; i++) {
                    columnValue = resultSet.getString(i);
                    if(i == 1) {
                        previousID = columnValue;
                    }
                    resultStr += columnValue;
                    if(i != columnsNumber) {
                        resultStr += " | ";
                    }
                }
                resultStr += "\n";
            }
            resultArea.setText(resultStr);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void dodajRekord() {
        String update = "INSERT INTO " + nazwaTabeli + " VALUES(" + addField.getText() + ");";

        try {
            statement = connection.createStatement();
            statement.executeUpdate(update);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void zmienRekord() {
        String result = resultArea.getText();
        if(countLines(result) == 3) {
            String nazwaPk = "id_" + nazwaTabeli.toLowerCase();
            String update = "UPDATE " + nazwaTabeli + " SET " + updateField.getText() + " WHERE " + nazwaPk + "=" + previousID + ";";
            statusField.setText("zmiana zakończona");

            try {
                statement = connection.createStatement();
                statement.executeUpdate(update);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        else {
            statusField.setText("zmiana nieudana");
        }
    }

    @FXML
    public void usunRekord() {
        String result = resultArea.getText();
        if(countLines(result) == 3) {
            String nazwaPk = "id_" + nazwaTabeli.toLowerCase();
            String update = "DELETE FROM " + nazwaTabeli + " WHERE " + nazwaPk + "=" + previousID + ";";
            statusField.setText("akcja zakończona");

            try {
                statement = connection.createStatement();
                statement.executeUpdate(update);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        else {
            statusField.setText("akcja nieudana");
        }
    }

    public void setNazwaTabeli(String nazwaTabeli) {
        this.nazwaTabeli = nazwaTabeli;
    }

    private static int countLines(String str){
        String[] lines = str.split("\r\n|\r|\n");
        return  lines.length;
    }
}
