package org.example.GameLogic.Algorithms;

import org.example.GameLogic.Board;
import org.example.GameLogic.Move;
import org.example.GameLogic.PiecePosition;

import java.util.ArrayList;
import java.util.List;

public class State {
    private Board board;
    private List<Move> availableMoves;
    private int value;

    public State(Board board) {
        this.board = board;
        this.availableMoves = new ArrayList<>();
    }

    public boolean isTerminal(int k) {
        return k == 1;
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
}
