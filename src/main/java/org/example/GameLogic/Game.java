package org.example.GameLogic;

import javafx.stage.Stage;
import org.example.GUI.GameInterface;
import org.example.GameLogic.Players.HumanPlayer;
import org.example.GameLogic.Players.IntelligentPlayer;
import org.example.GameLogic.Players.Player;

public class Game implements Runnable{

    private Player currentPlayer;

    public void initialize(Stage stage){
        //1. initialize GUI
        GameInterface game = new GameInterface(stage);
        game.initialize();

        //2. initialize Players
        HumanPlayer humanPlayer = new HumanPlayer("Emperor Napoleon", "white");
        IntelligentPlayer intelligentPlayer = new IntelligentPlayer("Marshall Kutuzov", "black");
        currentPlayer = humanPlayer;

    }


    @Override
    public void run() {
        while(true){
            currentPlayer.makeMove();
        }
    }
}
