package com.example.guessthenumber17java;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Gamer {
    private static Gamer instance = new Gamer();
    private ObservableList<Game> games = FXCollections.observableArrayList();
    private ObjectProperty<Game> currentGame = new SimpleObjectProperty<Game>();

    public static Gamer getInstance() {
        return instance;
    }

    public void play(String level) {
        Game game = new Game(level);
        games.add(game);
        currentGame.set(game);
    }

    public ObservableList<Game> getGames() {
        return games;
    }

    public Game getCurrentGame() {
        return currentGame.get();
    }
}
