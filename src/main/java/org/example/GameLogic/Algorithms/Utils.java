package org.example.GameLogic.Algorithms;

import org.example.GameLogic.*;
import org.example.GameLogic.Players.Referee;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Utils {
    public static State makeAction(State state, Move action) {
        Board board = state.getBoard();
        Referee referee = new Referee();
        State newState =new State(createNewBoard(board,action), action);
        if(state.getOpponentBoard() != null) {
            if (referee.announce(state, action).equals("CHECK")) {
                newState.setTerminal();
            }
        }
        return newState;
    }

    private static Board createNewBoard(Board oldBoard, Move move){
        Board newBoard = new Board(oldBoard.getPosition());
        List<PiecePosition> newConfiguration = new ArrayList<>();
        List<PiecePosition> oldConfiguration = oldBoard.getConfiguration();
        for(PiecePosition piecePosition : oldConfiguration){
            PiecePosition newPiecePostion = new PiecePosition(piecePosition);
            if(newPiecePostion.getPosition().equals(move.getInitial())){
                newPiecePostion.setPieceType(PieceType.NONE);
            }
            if(newPiecePostion.getPosition().equals(move.getTarget())){
                newPiecePostion.setPieceType(move.getPieceType());
            }
            newConfiguration.add(newPiecePostion);

        }
        newBoard.setConfiguration(newConfiguration);
        return newBoard;
    }

    public static List<Move> availableMoves(State state) {
        List<PiecePosition> configuration = state.getBoard().getConfiguration();
        List<Position> allPositions = allPositions();
        List<Move> availableMoves = new ArrayList<>();

        for (PiecePosition piecePosition : configuration) {
            PieceType pieceType = piecePosition.getPieceType();
            if(pieceType != PieceType.NONE) {
                Position position = piecePosition.getPosition();
                for (Position possiblePosition : allPositions) {
                    Move possibleMove = new Move(position, possiblePosition, pieceType, state.getBoard());
                    if (possibleMove.refisMoveLegal()) {
                        availableMoves.add(possibleMove);
                    }
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

    private static List<Position> allPositionsUP() {
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
