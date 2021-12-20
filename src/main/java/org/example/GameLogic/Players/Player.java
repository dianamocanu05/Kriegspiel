package org.example.GameLogic.Players;

import org.example.GameLogic.Board;
import org.example.GameLogic.Game;
import org.example.GameLogic.Move;
import org.example.GameLogic.PiecePosition;

public abstract class Player {

    private String name;
    private String color;
    private Board board;
    private Move attemptedMove;
    protected Game game;

    public Player(String name, String color, Game game){
        this.name = name;
        this.color = color;
        this.game = game;
    }

    public void setBoard(Board board){
        this.board = board;
    }

    public Board getBoard(){
        return board;
    }


    public abstract void setAttemptedMove(Move move);
    public Move getAttemptedMove(){
        return this.attemptedMove;
    }

    public String getName(){
        return name;
    }

    public String getColor(){
        return color;
    }

    public abstract void waitTurn();

    ;


}
