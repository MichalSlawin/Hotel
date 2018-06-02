package hotel;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TabeleModel {

    public void start() throws Exception{
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("tabele.fxml"));
        primaryStage.setTitle("Tabele");
        primaryStage.setScene(new Scene(root, 500, 500));
        primaryStage.show();
    }
}
