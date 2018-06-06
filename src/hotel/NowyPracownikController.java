package hotel;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import login.DbConnection;

import java.sql.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class NowyPracownikController {

    private ObservableList<String> adresyList = FXCollections.observableArrayList();
    private List<Integer> idAdresyList = new ArrayList<Integer>();
    private Connection connection = DbConnection.getConnection();
    private Statement statement;
    private ResultSet resultSet;

    @FXML
    private TextField imieField;
    @FXML
    private TextField nazwiskoField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField telefonField;
    @FXML
    private TextField peselField;
    @FXML
    private TextField stanowiskoField;
    @FXML
    private TextField pensjaField;
    @FXML
    private ComboBox adresComboBox;


    @FXML
    public void dodajPracownika() {
        int adresId = idAdresyList.get(adresComboBox.getSelectionModel().getSelectedIndex());
        String imie = imieField.getText();
        String nazwisko = nazwiskoField.getText();
        String email = emailField.getText();
        String telefon = telefonField.getText();
        String pesel = peselField.getText();
        String stanowisko = stanowiskoField.getText();
        String pensja = pensjaField.getText();

        String updateKlient = "INSERT INTO Pracownik VALUES('" + imie + "', '" + nazwisko +
                "', '" + email + "', '" + telefon + "', '" + pesel + "', '" + stanowisko + "', " + pensja + ", " + adresId + ");";
        try {
            statement = connection.createStatement();
            statement.executeUpdate(updateKlient);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Stage stage = (Stage) imieField.getScene().getWindow();
        stage.close();
    }

    public void inicjalizuj() {
        String adresQuery = "SELECT id_adres, ulica, numer, kod_pocztowy, miasto FROM Adres;";
        setList(adresyList, adresQuery, idAdresyList);
        adresComboBox.setItems(adresyList);
    }

    public void setList(ObservableList<String> observableList, String query, List<Integer> idList) {
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);

            ResultSetMetaData rsmd = resultSet.getMetaData();
            int columnsNumber = rsmd.getColumnCount();
            String columnValue;
            while (resultSet.next()) {
                String resultStr = "";
                for (int i = 1; i <= columnsNumber; i++) {
                    if(i == 1) {
                        idList.add(resultSet.getInt(i));
                    }
                    else {
                        columnValue = resultSet.getString(i);
                        resultStr += columnValue;
                        if (i != columnsNumber) {
                            resultStr += ", ";
                        }
                    }
                }
                observableList.add(resultStr);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
