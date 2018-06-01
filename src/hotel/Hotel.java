package hotel;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import login.LoginModel;

import java.sql.Connection;

public class Hotel {

    public void start() throws Exception{
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("hotel.fxml"));
        primaryStage.setTitle("Hotel");
        primaryStage.setScene(new Scene(root, 500, 500));
        primaryStage.show();
    }
}
