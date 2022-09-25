package com.example.guessthenumber17java;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML
    private String level;

    @FXML
    private ChoiceBox<String> levelBox;

    @FXML
    private Label games;

    @FXML
    private void onGameButtonClick() throws IOException {
        Gamer.getInstance().play(levelBox.getValue());
        Stage stage = (Stage) levelBox.getScene().getWindow();
        stage.close();
        Parent root = FXMLLoader.load(getClass().getResource("game.fxml"));
        stage.setScene(new Scene(root, 320, 240));
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        games.setText("Сыграно игр: " + Gamer.getInstance().getGames().size());
    }
}