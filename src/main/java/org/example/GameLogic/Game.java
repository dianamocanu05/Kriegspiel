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
    private Player currentPlayer;
    private Referee referee;
    private List<Player> players;
    private Stage stage;


    public Game(Stage stage) {
        this.stage = stage;
        players = new ArrayList<>();
    }

    private void initPlayers() {
        for (Player player : players) {
            Thread thread = new Thread(player);
            player.setThread(thread);
            thread.start();
        }
    }

    public void start() {
        referee = new Referee();
        currentPlayer = players.get(0);
        GUI = new GameInterface(this.stage);
        GUI.setGame(this);
        GUI.initialize();
        initPlayers();
    }


    public void switchPlayer() {
        this.currentPlayer = players.get(1 - players.indexOf(currentPlayer));
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void update() throws InterruptedException {

        Move move = GUI.lastMove;
        referee.announce(currentPlayer, move);
        GUI.displayButler();
        GUI.removeCurrentPlayerName();
        GUI.removeButler();
        switchPlayer();
        GUI.displayCurrentPlayerName();
    }


    public void addPlayer(Player player) {
        players.add(player);
    }

    public GameInterface getGUI() {
        return GUI;
    }

}
