package org.example.GameLogic.Algorithms;

import org.example.GameLogic.Board;
import org.example.GameLogic.Move;
import org.example.GameLogic.PiecePosition;

import java.util.ArrayList;
import java.util.List;

/**
 * State represents the configuration of the board at a given instant
 */
public class State {
    private Board board;
    private List<Move> availableMoves;
    private int value;
    private Move creatorMove;
    private Board opponentBoard;
    private boolean terminal;

    public State(Board board, Move creatorMove) {
        this.board = board;
        this.creatorMove = creatorMove;
        this.availableMoves = new ArrayList<>();
    }

    public void setOpponentBoard(Board opponentBoard){
        this.opponentBoard = opponentBoard;
    }

    public Board getOpponentBoard(){
        return opponentBoard;
    }




    public Move getCreatorMove(){
        return creatorMove;
    }

    public List<Move> getAvailableActions() {

        return Utils.availableMoves(this);
    }

    public Board getBoard() {
        return board;
    }

    public void print() {
        List<PiecePosition> configuration = board.getConfiguration();
        for (PiecePosition piecePosition : configuration) {
            if (!piecePosition.getPieceType().toString().equals("NONE")) {
                System.out.println(piecePosition.getPieceType().toString() + " " + piecePosition.getPosition().print());
            }
        }
    }


    public void setTerminal(){
        this.terminal = true;
    }

    public boolean isTerminal() {
        return terminal;
    }
}
