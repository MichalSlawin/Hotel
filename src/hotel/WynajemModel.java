package hotel;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class WynajemModel {
    public void start() throws Exception{
        Stage primaryStage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("wynajem.fxml"));
        Parent root = (Parent)fxmlLoader.load();
        WynajemController controller = fxmlLoader.<WynajemController>getController();
        controller.inicjalizuj();
        primaryStage.setTitle("Wynajem");
        primaryStage.setScene(new Scene(root, 500, 400));
        primaryStage.show();
    }
}
