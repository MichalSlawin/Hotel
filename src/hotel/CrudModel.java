package hotel;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import login.DbConnection;

import java.sql.*;

public class CrudModel {
    private String nazwaTabeli;

    public CrudModel(String nazwaTabeli) {
        this.nazwaTabeli = nazwaTabeli;
    }

    public void start() throws Exception{
        Stage primaryStage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("crud.fxml"));
        Parent root = (Parent)fxmlLoader.load();
        CrudController controller = fxmlLoader.<CrudController>getController();
        controller.setNazwaTabeli(nazwaTabeli);
        controller.setAddHelpField();
        primaryStage.setTitle("CRUD");
        primaryStage.setScene(new Scene(root, 900, 700));
        primaryStage.show();
    }
}
