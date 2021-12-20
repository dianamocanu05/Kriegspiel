package org.example.GameLogic.Players;

import org.example.GameLogic.Board;
import org.example.GameLogic.Game;
import org.example.GameLogic.Move;
import org.example.GameLogic.PiecePosition;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public abstract class Player {

    private String name;
    private String color;
    private Board board;
    protected  Game game;
    private final AtomicBoolean signal = new AtomicBoolean(false);


    public Player(String name, String color) {
        this.name = name;
        this.color = color;
    }

    public Move attemptMove(){
        Move move;
        synchronized (game.getGUI().mutex){
            while(!game.getGUI().chosen){
                try{
                    game.getGUI().mutex.wait();
                }catch (InterruptedException exc){
                    exc.printStackTrace();
                }
            }
        }
        move = game.getGUI().lastMove;
        return move;
    }

    public void setBoard(Board board) {
        this.board = board;
    }


    public void setGame(Game game) {
        this.game = game;
    }

    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }

    public Board getBoard() {
        return board;
    }




    public void run() {
        while (true){
            while(!signal.get()){
                synchronized (signal){
                    try {
                        signal.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            signal.set(false);

            System.out.println(this.getName() + "'S TURN");

            Move move = attemptMove();
            Player oldPlayer = this;
            List<PiecePosition> newConf = this.board.replace(move);
            Board board = new Board();
            board.setConfiguration(newConf);
            this.board = board;
            game.updatePlayer(oldPlayer, this);
            game.update();

            signal.set(true);
            signal.notify();
        }
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Player)) {
            return false;
        }
        return this.name.equals(((Player) other).getName());
    }


}
