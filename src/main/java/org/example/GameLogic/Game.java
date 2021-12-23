package org.example.GameLogic;

import javafx.application.Platform;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.example.GUI.GameInterface;
import org.example.GUI.Utils;
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
    private Text name, butlerMessage;
    private Logger logger;


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
        logger = new Logger();
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
        while(name ==null) {
            name = GUI.getPlayerName();
        }

        Move move = currentPlayer.getLastMove();
        String message = referee.announce(currentPlayer, move);
        logger.addLog("Referee to " + currentPlayer.getName() + ": " + message);
        butlerMessage = Utils.addButlerMessage(GUI.getGamePane(), message);
        Thread.sleep(1000);
        Utils.removeNameText(name,GUI.getGamePane());
        Utils.removeButlerMessage(GUI.getGamePane(),butlerMessage);

        switchPlayer();
        name = Utils.addNameText(currentPlayer.getName(),GUI.getGamePane());

    }


    public void addPlayer(Player player) {
        players.add(player);
    }

    public GameInterface getGUI() {
        return GUI;
    }

    public Referee getReferee(){ return referee;}

}
