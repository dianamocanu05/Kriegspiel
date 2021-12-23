package org.example.GameLogic;

import javafx.geometry.Pos;
import org.example.GameLogic.PieceType;
import org.example.GameLogic.Players.Player;
import org.example.GameLogic.Position;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Move {

    private Position initial, target;
    private PieceType pieceType;
    private Board board;
    private Player player;
    private static final List<String> diagonalDirections = Arrays.asList("RIGHT_UP", "RIGHT_DOWN", "LEFT_UP", "LEFT_DOWN");

    public Move(Position initial, Position target, PieceType pieceType, Board board, Player player){
        this.board = board;
        this.initial = initial;
        this.target = target;
        this.player = player;
        this.pieceType = pieceType;
    }

    public PieceType getPieceType(){
        return pieceType;
    }

    public Position getInitial(){
        return initial;
    }

    public Position getTarget(){
        return target;
    }


   public  boolean isMoveLegal(){
        switch (pieceType){
            /*
             * Kings move one square in any direction, so long as that square
             * is not attacked by an enemy piece. Additionally, kings are able
             * to make a special move, known as castling.
             */
            case KING:{
                return ((getDistance() == 1) && !(moveContainsEnemyPiece()));
            }

            /*
             * Queens move diagonally, horizontally, or vertically
             * any number of squares. They are unable to jump over pieces.
             */
            case QUEEN:{
                return !moveContainsEnemyPiece();
            }

            /*
             * Rooks move horizontally or vertically any number of squares.
             * They are unable to jump over pieces. Rooks move when the king castles.
             */
            case ROOK:{
                return ((Objects.equals(computeDirection(), "UP") || Objects.equals(computeDirection(), "DOWN")) && (!moveContainsEnemyPiece()));
            }

            /*
            * Bishops move diagonally any number of squares.
            *  They are unable to jump over pieces.
            */
            case BISHOP:{
                return (diagonalDirections.contains(computeDirection()) && !moveContainsEnemyPiece());
            }

            /*
             * Knights move in an ‘L’ shape’: two squares in a horizontal or vertical direction,
             * then move one square horizontally or vertically.
             * They are the only piece able to jump over other pieces.
             */
            case KNIGHT:{
                return isLMove();
            }

            /*
             * Pawns move vertically forward one square, with the option to move two squares
             * if they have not yet moved. Pawns are the only piece to capture
             * different to how they move. The pawns capture one square
             * diagonally in a forward direction
             */
            case PAWN:{
                return (Objects.equals(computeDirection(), "UP") && getDistance() ==1);
            }
        }
        return false;
    }

    /**
     * Work in progress
     */
    private  int getDistance(){
       char initialLetter = initial.getLetter();
       int initialNumber = initial.getNumber();

       char targetLetter = target.getLetter();
       int targetNumber = target.getNumber();

       if(initialLetter == targetLetter){
           return Math.abs(initialNumber - targetNumber);
       }

       if(initialNumber == targetNumber){
           return Math.abs((initialLetter - 'A') - (targetLetter - 'A'));
       }

       return -1;
    }

    private  String computeDirection(){
        if(initial.getNumber() == target.getNumber()){ //HORIZONTALLY
            if(initial.getLetter() - 'A' < target.getLetter() - 'A'){
                return "RIGHT";
            }
            return "LEFT";
        }

        if(initial.getLetter() == target.getLetter()){ //VERTICALLY
            if(initial.getNumber() < target.getNumber()){
                return "UP";
            }
            return "DOWN";
        }

        int delta = initial.getLetter() - target.getLetter();
        if(delta > 0) {
            if (initial.getNumber() - target.getNumber() == delta) {
                return "RIGHT_UP";
            }
            if (initial.getNumber() - target.getNumber() == -1 * delta) {
                return "RIGHT_DOWN";
            }
        }
        else{
            if (initial.getNumber() - target.getNumber() == delta) {
                return "LEFT_UP";
            }
            if (initial.getNumber() - target.getNumber() == -1 * delta) {
                return "LEFT_DOWN";
            }
        }

        return null;
    }

    private  boolean moveContainsEnemyPiece(){
        String direction = computeDirection();
        switch (direction){
            case "UP":{
                int initialNumber = initial.getNumber();
                int targetNumber = target.getNumber();

                for(int i=initialNumber+1; i<=targetNumber; i++){
                    if(board.getPieceAtPosition(new Position(initial.getLetter(), i)) != PieceType.NONE){
                        return true;
                    }
                }
                return false;
            }

            case "DOWN":{
                int initialNumber = initial.getNumber();
                int targetNumber = target.getNumber();

                for(int i=targetNumber; i<initialNumber; i++){
                    if(board.getPieceAtPosition(new Position(initial.getLetter(), i)) != PieceType.NONE){
                        return true;
                    }
                }
                return false;
            }

            case "RIGHT":{
                char initialLetter = initial.getLetter();
                char targetLetter = target.getLetter();

                for(int i=initialLetter-'A'+1; i<=targetLetter-'A';i++){
                    if(board.getPieceAtPosition(new Position((char)( i + 'A'), initial.getNumber()))!= PieceType.NONE){
                        return true;
                    }
                }
                return false;
            }

            case "LEFT":{
                char initialLetter = initial.getLetter();
                char targetLetter = target.getLetter();

                for(int i=targetLetter-'A'; i<initialLetter-'A';i++){
                    if(board.getPieceAtPosition(new Position((char)( i + 'A'), initial.getNumber()))!= PieceType.NONE){
                        return true;
                    }
                }
                return false;
            }



        }
        return false;
    }

    private boolean isLMove(){
        int initialNumber = initial.getNumber();
        char initialLetter = initial.getLetter();

        int targetNumber = target.getNumber();
        char targetLetter = target.getLetter();
        int delta = (initialLetter - targetLetter)*(initialNumber - targetNumber);
        return ((delta == 2) || (delta == -2));

        /*
        if((initialLetter - targetLetter == 1) && (initialNumber - targetNumber == -2)){
            return true;
        }
        if((initialLetter - targetLetter == 2) && (initialNumber - targetNumber == -1)){
            return true;
        }
        if((initialLetter - targetLetter == 2) && (initialNumber - targetNumber == 1)){
            return true;
        }
        if((initialLetter - targetLetter == 1) && (initialNumber - targetNumber == 2)){
            return true;
        }
        if((initialLetter - targetLetter == -1) && (initialNumber - targetNumber == 2)){
            return true;
        }
        if((initialLetter - targetLetter == -1) && (initialNumber - targetNumber == -2)){
            return true;
        }
        if((initialLetter - targetLetter == -2) && (initialNumber - targetNumber == -1)){
            return true;
        }
        if((initialLetter - targetLetter == -2) && (initialNumber - targetNumber == 1)){
            return true;
        }
        */

    }
}
