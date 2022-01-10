package org.example.GameLogic.Players;

import org.example.GameLogic.Board;
import org.example.GameLogic.Game;
import org.example.GameLogic.Move;
import org.example.GameLogic.PiecePosition;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public abstract class Player implements Runnable {

    private String name;
    private String color;
    protected Board board;
    protected Game game;
    private Thread thread;
    protected Move lastMove;
    private Player opponent;

    public Player(String name, String color) {
        this.name = name;
        this.color = color;
    }

    public Move getLastMove() {
        return lastMove;
    }

    public void setOpponent(Player opponent) {
        this.opponent = opponent;
    }

    public Player getOpponent(){return  opponent;}

    public abstract Move attemptMove() throws InterruptedException;

    public void setBoard(Board board) {
        this.board = board;
    }


    public void setGame(Game game) {
        this.game = game;
    }

    public Game getGame() {
        return game;
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

            while (!game.getCurrentPlayer().equals(this)) {
                this.game.wait();
            }

        }

    }

    public void setThread(Thread thread) {
        this.thread = thread;
    }


    @Override
    public void run() {
        while (true) {
            try {
                waitTurn();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(this.getName() + "'S TURN");

            Move move = null;
            try {
                move = attemptMove();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (this instanceof HumanPlayer) {
                game.getGUI().chosen = false;
            }

            try {
                game.update();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (game) {
                this.game.notify();
            }

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
