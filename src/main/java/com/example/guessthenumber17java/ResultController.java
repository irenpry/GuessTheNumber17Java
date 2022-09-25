package com.example.guessthenumber17java;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class ResultController {
    private Game game;
    private int curNum;
    private GameController parent;

    @FXML
    private Label resultMessage;

    @FXML
    private Button okButton;

    @FXML
    private Button mainButton;

    @FXML
    private Button againButton;

    public void setParent (GameController parent){
        this.parent = parent;
    }

    @FXML
    public void onOkButtonClick() {
        Stage stage = (Stage) resultMessage.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void onAgainButtonClick() throws IOException {
        parent.closeStage();
        Gamer.getInstance().play(game.getLevel());
        Stage stage = (Stage) resultMessage.getScene().getWindow();
        stage.close();
        Parent root = FXMLLoader.load(getClass().getResource("game.fxml"));
        stage.setTitle("Угадай число!");
        stage.setScene(new Scene(root, 320, 240));
        stage.show();
    }

    @FXML
    public void onMainButtonClick() throws IOException {
        parent.closeStage();
        Stage stage = (Stage) resultMessage.getScene().getWindow();
        stage.close();
        Parent root = FXMLLoader.load(getClass().getResource("main.fxml"));
        stage.setTitle("Угадай число!");
        stage.setScene(new Scene(root, 320, 240));
        stage.show();
    }

    public void initResult(Game game, int curNum) {
        this.game = game;
        this.curNum = curNum;
        if (game.getFinished()) {
            mainButton.setVisible(true);
            againButton.setVisible(true);
            okButton.setVisible(false);
        }
        if (game.getRetryCount() == 0) {
            resultMessage.setText("Прости, но попытки кончились.\nЗагаданное число" + game.getNumber());
            return;
        }
        if (game.getLastResult() == 0) {
            resultMessage.setText("Вау, ты отгадал, это число " + curNum + "!");
        } else if (game.getLastResult() > 0) {
            resultMessage.setText("Загаданное число больше " + curNum);
        } else if (game.getLastResult() < 0) {
            resultMessage.setText("Загаданное число меньше " + curNum);
        }
    }
}
