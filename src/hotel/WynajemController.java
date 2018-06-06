package hotel;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import login.DbConnection;

import java.sql.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class WynajemController {
    private ObservableList<String> pokojeList = FXCollections.observableArrayList();
    private List<Integer> idPokojeList = new ArrayList<Integer>();
    private ObservableList<String> klienciList = FXCollections.observableArrayList();
    private List<Integer> idKlienciList = new ArrayList<Integer>();
    private ObservableList<String> pracownicyList = FXCollections.observableArrayList();
    private List<Integer> idPracownicyList = new ArrayList<Integer>();
    private Connection connection = DbConnection.getConnection();
    private Statement statement;
    private ResultSet resultSet;

    @FXML
    private ComboBox pokojComboBox;
    @FXML
    private ComboBox klientComboBox;
    @FXML
    private ComboBox pracownikComboBox;
    @FXML
    private DatePicker wynajemDate;
    @FXML
    private DatePicker oddanieDate;
    @FXML
    private TextField oplataTextField;
    @FXML
    private TextField statusField;

    @FXML
    public void dodajPokoj() {

    }

    @FXML
    public void dodajWynajem() {
        int pokojId = idPokojeList.get(pokojComboBox.getSelectionModel().getSelectedIndex());
        int klientId = idKlienciList.get(klientComboBox.getSelectionModel().getSelectedIndex());
        int pracownikId = idPracownicyList.get(pracownikComboBox.getSelectionModel().getSelectedIndex());
        String dataWynajmu = wynajemDate.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String dataOddania = oddanieDate.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String oplata = oplataTextField.getText();

        String updateWynajem = "INSERT INTO Wynajem VALUES('" + dataWynajmu + "', '" + dataOddania +
                "', " + oplata + ", " + pracownikId + ", " + klientId + ");";
        String updatePokoje = "INSERT INTO Pokoje VALUES(" + pokojId + ", " + "IDENT_CURRENT( 'Wynajem' )  " + ");";
        try {
            statement = connection.createStatement();
            statement.executeUpdate(updateWynajem);
            statement.executeUpdate(updatePokoje);
            statusField.setText("dodano");
        } catch (SQLException e) {
            e.printStackTrace();
            statusField.setText("dodanie nieudane");
        }

    }



    public void inicjalizuj() {
        String pokojQuery = "SELECT id_pokoj, numer, ilu_osobowy, wyposazenie FROM pokoj;";
        setList(pokojeList, pokojQuery, idPokojeList);
        pokojComboBox.setItems(pokojeList);

        String klientQuery = "SELECT id_klient, k.imie, k.nazwisko, k.email, k.telefon, a.ulica + a.numer + ' ' + a.kod_pocztowy AS Adres\n" +
                "FROM Klient k INNER JOIN Adres a ON k.Adres_id_adres = a.id_adres";
        setList(klienciList, klientQuery, idKlienciList);
        klientComboBox.setItems(klienciList);

        String pracownikQuery = "SELECT id_pracownik, p.imie, p.nazwisko, p.email, p.telefon, p.pesel, p.stanowisko, p.pensja, a.ulica + a.numer + ' ' + a.kod_pocztowy AS Adres\n" +
                "FROM Pracownik p INNER JOIN Adres a ON p.Adres_id_adres = a.id_adres";
        setList(pracownicyList, pracownikQuery, idPracownicyList);
        pracownikComboBox.setItems(pracownicyList);
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
