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
                //resultSet = statement.executeQuery("SELECT * FROM " + nazwaTabeli + ";");
                resultSet = statement.executeQuery(setQuery() + ";");
            }
            else {
                //resultSet = statement.executeQuery("SELECT * FROM " + nazwaTabeli + " WHERE " + readField.getText() + ";");
                resultSet = statement.executeQuery(setQuery() + " WHERE " + readField.getText() + ";");
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
                    resultStr += columnValue;
                    if(i != columnsNumber) {
                        resultStr += " | ";
                    }
                }
                resultStr += "\n";
            }
            resultArea.setText(resultStr);
            statusField.setText("wyświetlono");

        } catch (SQLException e) {
            e.printStackTrace();
            statusField.setText("wyświetlenie nieudane");
        }
    }

    private String setQuery() {
        Statement idStatement = null;
        try {
            idStatement = connection.createStatement();
            ResultSet idResultSet = idStatement.executeQuery("Select * FROM " + nazwaTabeli + ";");
            idResultSet.next();
            previousID = idResultSet.getString(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        switch(nazwaTabeli) {
            case "Wynajem":
                return "SELECT p.imie + ' ' + p.nazwisko AS Pracownik, k.imie + ' ' + k.nazwisko AS Klient, w.data_wynajmu, w.data_oddania, w.oplata\n" +
                        "FROM Pracownik p INNER JOIN Wynajem w ON w.Pracownik_id_pracownik = p.id_pracownik\n" +
                        "INNER JOIN Klient k ON w.Klient_id_klient = k.id_klient";
            case "Klient":
                return "SELECT k.imie, k.nazwisko, k.email, k.telefon, a.ulica + a.numer + ' ' + a.kod_pocztowy AS Adres\n" +
                        "FROM Klient k INNER JOIN Adres a ON k.Adres_id_adres = a.id_adres";
            case "Pokoj":
                return "SELECT numer, ilu_osobowy, wyposazenie FROM Pokoj";
            case "Pracownik":
                return "SELECT p.imie, p.nazwisko, p.email, p.telefon, p.pesel, p.stanowisko, p.pensja, a.ulica + a.numer + ' ' + a.kod_pocztowy AS Adres\n" +
                        "FROM Pracownik p INNER JOIN Adres a ON p.Adres_id_adres = a.id_adres";
            case "Adres":
                return "SELECT ulica, numer, kod_pocztowy, miasto FROM adres";
        }
        return null;
    }

    @FXML
    public void dodajRekord() {
        String update = "INSERT INTO " + nazwaTabeli + " VALUES(" + addField.getText() + ");";

        try {
            statement = connection.createStatement();
            statement.executeUpdate(update);
            statusField.setText("dodano");
        } catch (SQLException e) {
            e.printStackTrace();
            statusField.setText("dodanie nieudane");
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
                statusField.setText("zmiana nieudana");
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
            statusField.setText("usunięto");

            try {
                statement = connection.createStatement();
                statement.executeUpdate(update);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        else {
            statusField.setText("usuwanie nieudane");
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
