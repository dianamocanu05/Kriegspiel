package org.example.GameLogic.Players;

import org.example.GameLogic.Board;
import org.example.GameLogic.Game;
import org.example.GameLogic.Move;
import org.example.GameLogic.PiecePosition;

public abstract class Player implements Runnable {

    private String name;
    private String color;
    private Board board;
    protected Thread thread;
    protected Game game;

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

    public void setThread(Thread thread) {
        this.thread = thread;
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

    private void waitTurn() throws InterruptedException {
        synchronized (game) {
            while (game.getCurrentPlayer().equals(this)) {
                this.game.wait();
            }
        }
    }


    @Override
    public void run() {
        while (true){
            try{
                waitTurn();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(this.getName() + "'S TURN");
            Move move = attemptMove();
            this.board.replace(move);
            game.update();
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
