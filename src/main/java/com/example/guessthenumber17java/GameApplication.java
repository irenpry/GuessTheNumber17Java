package com.example.guessthenumber17java;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class GameApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("main.fxml"));
        stage.setScene(new Scene(root, 320, 240));
        stage.setTitle("Угадай число!");
        stage.show();
        Gamer.getInstance();
    }

    public static void main(String[] args) {
        launch();
    }
}