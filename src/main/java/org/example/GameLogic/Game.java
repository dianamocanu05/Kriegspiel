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

import java.text.DecimalFormat;
import java.text.NumberFormat;
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
            player.setOpponent(players.get(1 - players.indexOf(player)));
            thread.start();
        }
    }


    public void start() {
        logger = new Logger();
        referee = new Referee(this);
        GUI = new GameInterface(this.stage);
        GUI.setGame(this);
        GUI.initialize();
    }

    public void startGameMode(String gameMode){
        if (gameMode.equals("AvA")){
            InitPlayers.initAvA(this);
        }else{
            InitPlayers.initPvA(this);
        }
        currentPlayer = players.get(0);
        initPlayers();
    }


    public void switchPlayer() {
        this.currentPlayer = players.get(1 - players.indexOf(currentPlayer));
    }

    public Player getOtherPlayer() {
        return players.get(1 - players.indexOf(currentPlayer));
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public boolean isEnded() {
        return false;
    }

    public Player getOtherPlayer(Player currentPlayer){
        int index = players.indexOf(currentPlayer);
        return players.get(1-index);
    }


    public void update() throws InterruptedException {
        while (name == null) {
            name = GUI.getPlayerName();
        }
        //get current move
        Move move = currentPlayer.getLastMove();
        move.print();

        //announce move feedback
        String message = referee.announce(currentPlayer, move);

        //make GUI perform action accordingly (move piece, delete piece)
        if(message.contains("YES") && currentPlayer instanceof IntelligentPlayer){
            GUI.movePiece(move,currentPlayer);
        }else if(message.contains("CAPTURE")){
            if(currentPlayer instanceof IntelligentPlayer) {
                GUI.movePiece(move, currentPlayer);
            }
            GUI.removePiece(move.getTarget(),getOtherPlayer(currentPlayer),move);
        }else if(message.contains("CHECK")){
            butlerMessage = Utils.addButlerMessage(GUI.getGamePane(), message);
            Thread.sleep(3000);
            GUI.close();
        }
        //logging in the 'War Chronicle' panel
        logOutcome(message, move);

        Utils.updateHistory(GUI.getHistory(), logger);
        butlerMessage = Utils.addButlerMessage(GUI.getGamePane(), message);
        Thread.sleep(1000);
        Utils.removeNameText(name, GUI.getGamePane());
        Utils.removeButlerMessage(GUI.getGamePane(), butlerMessage);

        //switch player
        switchPlayer();
        name = Utils.addNameText(currentPlayer.getName(), GUI.getGamePane());

    }

    private void logOutcome(String message, Move move) {

        String verb;
        if (message.contains("YES")) {
            verb = " moved ";
        } else {
            verb = " attempted to move ";
        }

        long time = System.currentTimeMillis() - GUI.getStart();
        NumberFormat formatter = new DecimalFormat("#0.00000");

        logger.addLog((formatter.format((time) / 1000d) + " : " + currentPlayer.getName() + verb + move.getPieceType().toString() + " to " + move.getTarget().print()));
    }


    public void addPlayer(Player player) {
        players.add(player);
    }

    public GameInterface getGUI() {
        return GUI;
    }

    public Referee getReferee() {
        return referee;
    }

}
