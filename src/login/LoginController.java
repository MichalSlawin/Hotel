package login;

import hotel.Hotel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController {


    @FXML
    private TextField statusField;
    @FXML
    private TextField loginField;
    @FXML
    private PasswordField passwordField;

    public LoginController() {}

    @FXML
    public void login() {
        LoginModel loginModel = new LoginModel(loginField.getText(), passwordField.getText());
        if(loginModel.isDatabaseConnected()) {
            this.statusField.setText("Połączono");

            hotel.Hotel hotel = new Hotel();
            try {
                hotel.start();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else {
            this.statusField.setText("Brak połączenia");
        }
    }
}
