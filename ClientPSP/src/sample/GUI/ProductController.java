package sample.GUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

public class ProductController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button BackButton;

    @FXML
    private TableView<?> ProductTable;

    @FXML
    private Button DoSmthButton;

    @FXML
    private Button CategoryButton;

    @FXML
    void initialize() {
        BackButton.setOnAction(event -> {
            openNewScene("/sample/GUI/MenuGUI.fxml");
        });

        CategoryButton.setOnAction(event -> {
            openNewScene("/sample/GUI/CategoryGUI.fxml");
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


