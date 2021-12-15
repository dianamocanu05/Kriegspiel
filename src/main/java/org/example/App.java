package org.example;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.example.GUI.GameInterface;

/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start(Stage stage) {
        GameInterface game = new GameInterface(stage);
        game.initialize();
    }

    public static void main(String[] args) {
        launch();
    }

}