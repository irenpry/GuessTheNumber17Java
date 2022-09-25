package com.example.guessthenumber17java;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static java.lang.Integer.parseInt;

public class GameController implements Initializable {
    private Game game = Gamer.getInstance().getCurrentGame();
    private ResultController children;

    @FXML
    private Text min;

    @FXML
    private Text max;

    @FXML
    private Label level;

    @FXML
    private Label retryCounter;

    @FXML
    private TextField currentNumber;

    @FXML
    public void onTryButtonClick() throws IOException {
        int curNum;

        if (parseInt(min.getText()) >= parseInt(currentNumber.getText()) ||
                parseInt(max.getText()) <= parseInt(currentNumber.getText())) {
            currentNumber.setBorder(new Border(new BorderStroke(Color.RED,
                    BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
            return;
        }

        try {
            curNum = parseInt(currentNumber.getText());
        } catch (Exception e) {
            currentNumber.setBorder(new Border(new BorderStroke(Color.RED,
                    BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
            return;
        }

        int compareResult = game.compareNumber(curNum);

        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("result.fxml"));
        Parent root = loader.load();
        children = loader.getController();
        children.setParent(this);
        children.initResult(game,curNum);
        stage.setScene(new Scene(root));
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(retryCounter.getScene().getWindow());
        stage.setTitle("Мы проверили");
        stage.showAndWait();

        if (compareResult == 0) {
        } else if (compareResult > 0) {
            min.setText(Integer.toString(curNum));
            retryCounter.setText("Осталось попыток: " + game.getRetryCount());
        } else {
            retryCounter.setText("Осталось попыток: " + game.getRetryCount());
            max.setText(Integer.toString(curNum));
        }

        currentNumber.clear();
        currentNumber.setBorder(new Border(new BorderStroke(Color.GRAY,
                BorderStrokeStyle.NONE, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
    }

    @FXML
    public void onMenuButtonClick() throws IOException {
        Stage stage = (Stage) retryCounter.getScene().getWindow();
        stage.close();
        Parent root = FXMLLoader.load(getClass().getResource("main.fxml"));
        stage.setTitle("Угадай число!");
        stage.setScene(new Scene(root, 320, 240));
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        retryCounter.setText("Осталось попыток: " + game.getRetryCount());
    }

    public void closeStage() {
        Stage stage = (Stage) retryCounter.getScene().getWindow();
        stage.close();
    }
}
