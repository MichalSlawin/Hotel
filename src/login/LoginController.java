package login;

import hotel.TabeleModel;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

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

            TabeleModel tabeleModel = new TabeleModel();
            try {
                tabeleModel.start();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else {
            this.statusField.setText("Brak połączenia");
        }
    }
}
