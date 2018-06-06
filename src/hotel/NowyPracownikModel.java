package hotel;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class NowyPracownikModel {
    public void start() throws Exception{
        Stage primaryStage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("nowyPracownik.fxml"));
        Parent root = (Parent)fxmlLoader.load();
        NowyPracownikController controller = fxmlLoader.<NowyPracownikController>getController();
        controller.inicjalizuj();
        primaryStage.setTitle("Nowy Pracownik");
        primaryStage.setScene(new Scene(root, 400, 600));
        primaryStage.show();
    }
}
