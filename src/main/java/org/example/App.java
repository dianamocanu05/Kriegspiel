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


        GameInterface gameInterface = new GameInterface(stage);
        Game game = new Game();
        game.setGUI(gameInterface);


        Board boardOne = new Board();
        Board boardTwo = new Board();


        HumanPlayer humanPlayer = new HumanPlayer("Emperor Napoleon", "white");
        humanPlayer.setBoard(boardOne);
        humanPlayer.setGame(game);

        HumanPlayer intelligentPlayer = new HumanPlayer("Marshall Kutuzov", "black");
        intelligentPlayer.setBoard(boardTwo);
        intelligentPlayer.setGame(game);

        game.addPlayer(humanPlayer);
        game.addPlayer(intelligentPlayer);

        game.start();
    }

    public static void main(String[] args) {
        launch();
    }

}