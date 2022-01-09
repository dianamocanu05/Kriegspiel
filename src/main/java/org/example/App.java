package org.example;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.example.GUI.GameInterface;
import org.example.GameLogic.Board;
import org.example.GameLogic.Game;
import org.example.GameLogic.Players.HumanPlayer;
import org.example.GameLogic.Players.IntelligentPlayer;

/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start(Stage stage) {

        Game game = new Game(stage);
        game.start();

    }

    public static void main(String[] args) {
        launch();
    }

}