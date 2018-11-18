package sample.GUI;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
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

public class Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button loginSighUpButton;

    @FXML
    private Button authSighInButton;

    @FXML
    private TextField login_field;

    @FXML
    private PasswordField password_field;

    @FXML
    void initialize() {
        authSighInButton.setOnAction(event -> {
            String loginText = login_field.getText().trim();
            String loginPassword = password_field.getText().trim();

            if(!loginText.equals("") && !loginPassword.equals(""))
                loginUser(loginText, loginPassword);
            else
                System.out.println("Login and password is empty");
        });


        loginSighUpButton.setOnAction(event -> {
           openNewScene("/sample/GUI/RegistrationGUI.fxml");
        });
    }

            private void loginUser(String loginText, String loginPassword) {
                DatabaseHandler dbHandler = new DatabaseHandler();
                User user = new User();
                user.setLogin(loginText);
                user.setPassword(loginPassword);
                dbHandler.getUser(user);
                ResultSet result = dbHandler.getUser(user);

                int counter = 0;
                try {
                    while (result.next()) {
                        counter++;
                    }
                }catch (SQLException e){
                    e.printStackTrace();
                }

                if(counter >= 1) {
                    openNewScene("/sample/GUI/MenuGUI.fxml");
                }
                else {
                    System.out.println("Wrong!");
                }
            }
            public void openNewScene(String window) {
                loginSighUpButton.getScene().getWindow().hide();

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

