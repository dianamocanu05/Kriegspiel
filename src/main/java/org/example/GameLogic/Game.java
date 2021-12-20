package org.example.GameLogic;

import javafx.application.Platform;
import javafx.scene.effect.GaussianBlur;
import javafx.stage.Stage;
import org.example.GUI.GameInterface;
import org.example.GameLogic.Players.HumanPlayer;
import org.example.GameLogic.Players.IntelligentPlayer;
import org.example.GameLogic.Players.Player;
import org.example.GameLogic.Players.Referee;

import java.util.ArrayList;
import java.util.List;

public class Game {

    private GameInterface GUI;
    private Player currentPlayer, nextPlayer;
    private Referee referee;
    private List<Player> players;

    public Game() {
        players = new ArrayList<>();
    }

    public void initPlayers() {
        for (Player player : players) {
            Thread thread = new Thread(player);
            player.setThread(thread);
            thread.start();
        }
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public void switchPlayer() {
        this.currentPlayer = players.get(1 - players.indexOf(currentPlayer));
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void start() {

        setCurrentPlayer(players.get(0));
        initPlayers();
        GUI.setCurrentPlayer(currentPlayer);
        GUI.initialize();

    }


    public void addPlayer(Player player) {
        players.add(player);
    }

    public GameInterface getGUI() {
        return GUI;
    }

    public void setGUI(GameInterface GUI) {
        this.GUI = GUI;
    }

    public void update() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                Stage stage = GUI.getStage();
                GUI.gameInterface(stage);
            }
        });
        switchPlayer();
    }
}
