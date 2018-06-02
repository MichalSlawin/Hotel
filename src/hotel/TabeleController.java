package hotel;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import login.DbConnection;

import java.sql.*;


public class TabeleController {

    public TabeleController() {}

    @FXML
    public void zarzadzajWynajmami() {
        startCRUD("Wynajem");
    }

    @FXML
    public void zarzadzajKlientami() {
        startCRUD("Klient");
    }

    @FXML
    public void zarzadzajAdresami() {
        startCRUD("Adres");
    }

    @FXML
    public void zarzadzajPokojami() {
        startCRUD("Pokoj");
    }

    @FXML
    public void zarzadzajPracownikami() {
        startCRUD("Pracownik");
    }

    private void startCRUD(String nazwaTabeli) {
        CrudModel crudModel = new CrudModel(nazwaTabeli);
        try {
            crudModel.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
