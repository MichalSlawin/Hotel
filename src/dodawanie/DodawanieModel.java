package dodawanie;

import hotel.CrudController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class DodawanieModel {
    private String nazwaTabeli;

    public DodawanieModel(String nazwaTabeli) {
        this.nazwaTabeli = nazwaTabeli;
    }

    public void start() throws Exception{
        Stage primaryStage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("crud.fxml"));
        Parent root = (Parent)fxmlLoader.load();
        CrudController controller = fxmlLoader.<CrudController>getController();
        controller.setNazwaTabeli(nazwaTabeli);
        primaryStage.setTitle("CRUD");
        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.show();
    }
}
