package hotel;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class NowyKlientModel {
    public void start() throws Exception{
        Stage primaryStage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("nowyKlient.fxml"));
        Parent root = (Parent)fxmlLoader.load();
        NowyKlientController controller = fxmlLoader.<NowyKlientController>getController();
        controller.inicjalizuj();
        primaryStage.setTitle("Nowy Klient");
        primaryStage.setScene(new Scene(root, 400, 400));
        primaryStage.show();
    }
}
