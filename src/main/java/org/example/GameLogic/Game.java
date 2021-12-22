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
        try {
            Thread.sleep(2 * 1000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public Player getHumanPlayer(){
        for(Player player : players){
            if(player instanceof HumanPlayer){
                return player;
            }
        }
        return null;
    }

    public void update() throws InterruptedException {

        Move move = currentPlayer.getLastMove();
        String message = referee.announce(currentPlayer, move);
        GUI.displayButlerMessage(message);
        GUI.removeCurrentPlayerName();
        switchPlayer();
        GUI.displayCurrentPlayerName();
        GUI.removeButlerMessage();

    }


    public void addPlayer(Player player) {
        players.add(player);
    }

    public GameInterface getGUI() {
        return GUI;
    }

}
