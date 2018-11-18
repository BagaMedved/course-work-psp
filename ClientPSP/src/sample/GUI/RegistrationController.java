package sample.GUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.DatabaseHandler;
import sample.User;

public class RegistrationController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button RegistrationButton;

    @FXML
    private TextField login_field;

    @FXML
    private PasswordField password_field;

    @FXML
    private TextField username_field;

    @FXML
    void initialize() {
        DatabaseHandler dbHandler = new DatabaseHandler();
        RegistrationButton.setOnAction(event -> {

            sighUpNewUser();
            openNewScene("/sample/GUI/AuthorisationGUI.fxml");
        });
    }

    private void sighUpNewUser() {
        DatabaseHandler dbHandler = new DatabaseHandler();

        String userName = username_field.getText();
        String login = login_field.getText();
        String password = password_field.getText();

        User user = new User(userName,login,password);

        dbHandler.signUpUser(user);
    }
    public void openNewScene(String window) {
        RegistrationButton.getScene().getWindow().hide();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(window));

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.showAndWait();
    }
}

