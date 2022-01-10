package org.example.GameLogic.Algorithms;

import org.example.GameLogic.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Utils {
    public static State makeAction(State state, Move action) {
        System.out.println("Moved " + action.getPieceType().toString() + " from " + action.getInitial().print() + " to " + action.getTarget().print());
        Board board = state.getBoard();
        board.replace(action);
        return new State(board);
    }

    public static List<Move> availableMoves(State state) {
        List<PiecePosition> configuration = state.getBoard().getConfiguration();
        List<Position> allPositions = allPositions();
        List<Move> availableMoves = new ArrayList<>();
        for (PiecePosition piecePosition : configuration) {
            PieceType pieceType = piecePosition.getPieceType();
            Position position = piecePosition.getPosition();
            for (Position possiblePosition : allPositions) {
                Move possibleMove = new Move(position, possiblePosition, pieceType, state.getBoard());
                if (possibleMove.isMoveLegal()) {
                    availableMoves.add(possibleMove);
                }
            }
        }
        return availableMoves;
    }

    private static List<Position> allPositions() {
        List<Position> allPositions = new ArrayList<>();
        for (int i = 1; i <= 8; i++) {
            char letter = (char) ('A' + i - 1);
            for (int j = 1; j <= 8; j++) {
                allPositions.add(new Position(letter, j));
            }
        }
        return allPositions;
    }


}
