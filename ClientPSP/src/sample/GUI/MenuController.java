package sample.GUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class MenuController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button ProductButton;

    @FXML
    private Button ExitButton;

    @FXML
    private Button OrderButton;

    @FXML
    private Button BackButton;

    @FXML
    private Button ProviderButton;

    @FXML
    private Button ClientButton;

    @FXML
    void initialize() {
        BackButton.setOnAction(event -> {
            openNewScene("/sample/GUI/AuthorisationGUI.fxml");
        });
        ProductButton.setOnAction(event -> {
            openNewScene("/sample/GUI/ProductGUI.fxml");
        });
        ProviderButton.setOnAction(event -> {
            openNewScene("/sample/GUI/ProviderGUI.fxml");
        });
        ClientButton.setOnAction(event -> {
            openNewScene("/sample/GUI/ClientGUI.fxml");
        });
        OrderButton.setOnAction(event -> {
            openNewScene("/sample/GUI/OrderGUI.fxml");
        });

    }

    public void openNewScene(String window) {
        BackButton.getScene().getWindow().hide();

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

