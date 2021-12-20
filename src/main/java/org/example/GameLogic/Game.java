package org.example.GameLogic;

import javafx.stage.Stage;
import org.example.GUI.GameInterface;
import org.example.GameLogic.Players.HumanPlayer;
import org.example.GameLogic.Players.IntelligentPlayer;
import org.example.GameLogic.Players.Player;
import org.example.GameLogic.Players.Referee;

public class Game implements Runnable{

    private GameInterface gameInterface;
    private Player currentPlayer, nextPlayer;
    private Referee referee;

    public Game(){

    }

    public GameInterface getGameInterface(){
        return this.gameInterface;
    }
    public void initialize(Stage stage){
        //0. initialize boards
        Board boardOne = new Board();
        Board boardTwo = new Board();

        //1. initialize GUI
        gameInterface = new GameInterface(stage, boardOne);
        gameInterface.initialize();

        //2. initialize Players
        HumanPlayer humanPlayer = new HumanPlayer("Emperor Napoleon", "white", this);
        humanPlayer.setBoard(boardOne);

        IntelligentPlayer intelligentPlayer = new IntelligentPlayer("Marshall Kutuzov", "black", this);
        intelligentPlayer.setBoard(boardTwo);

        currentPlayer = humanPlayer;
        nextPlayer = intelligentPlayer;

        //3. initialize Referee
        referee = new Referee();

    }


    @Override
    public void run() {
        while(true){
            System.out.println(currentPlayer.getName() + " (" + currentPlayer.getColor() + ")'S TURN");

            gameInterface.setCurrentPlayer(currentPlayer);

            currentPlayer.waitTurn();
            Move attemptedMove = currentPlayer.getAttemptedMove();
            if (referee.announce(currentPlayer, attemptedMove)){
                gameInterface.printChessConfiguration();
            }
            nextPlayer = currentPlayer;
            currentPlayer = nextPlayer;

        }
    }

    public void updateBoard(Move move){
        Board board = currentPlayer.getBoard();
        board.replace(move);
        currentPlayer.setBoard(board);
    }
}
