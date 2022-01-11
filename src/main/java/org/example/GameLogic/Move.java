package org.example.GameLogic;

import org.example.GameLogic.Players.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Move {

    private Position initial, target;
    private PieceType pieceType;
    private Board thisBoard, enemyBoard;
    private Player player;
    private static final List<String> diagonalDirections = Arrays.asList("RIGHT_UP", "RIGHT_DOWN", "LEFT_UP", "LEFT_DOWN");
    private static final List<String> horizVerticalDirections = Arrays.asList("RIGHT", "LEFT", "UP", "DOWN");
    public Move(Position initial, Position target, PieceType pieceType, Board thisBoard,Board enemyBoard, Player player) {
        this.thisBoard = thisBoard;
        this.initial = initial;
        this.target = target;
        this.player = player;
        this.pieceType = pieceType;
        this.enemyBoard = enemyBoard;

    }

    public Move(Position initial, Position target, PieceType pieceType, Board thisBoard) {
        this.initial = initial;
        this.target = target;
        this.pieceType = pieceType;
        this.thisBoard = thisBoard;
    }

    public void setThisBoard(Board thisBoard){
        this.thisBoard = thisBoard;
    }

    public void setEnemyBoard(Board enemyBoard){
        this.enemyBoard = enemyBoard;
    }

    public PieceType getPieceType() {
        return pieceType;
    }

    public Position getInitial() {
        return initial;
    }

    public Position getTarget() {
        return target;
    }
    public boolean refisMoveLegal() {

        if(thisBoard.getPieceAtPosition(this.getTarget()) != PieceType.NONE){
            return false;
        }
        switch (pieceType) {
            /*
             * Kings move one square in any direction, so long as that square
             * is not attacked by an enemy piece. Additionally, kings are able
             * to make a special move, known as castling.
             */
            case KING: {
                //+castling
                return (getDistance() == 1);
            }

            /*
             * Queens move diagonally, horizontally, or vertically
             * any number of squares. They are unable to jump over pieces.
             */
            case QUEEN: {
                return !isJump(thisBoard) && (diagonalDirections.contains(computeDirection()) || (horizVerticalDirections.contains(computeDirection())));
            }

            /*
             * Rooks move horizontally or vertically any number of squares.
             * They are unable to jump over pieces. Rooks move when the king castles.
             */
            case ROOK: {
                return (horizVerticalDirections.contains(computeDirection()) && !isJump(thisBoard));
            }

            /*
             * Bishops move diagonally any number of squares.
             *  They are unable to jump over pieces.
             */
            case BISHOP: {
                return (diagonalDirections.contains(computeDirection()) && !isJump(thisBoard));
            }

            /*
             * Knights move in an ‘L’ shape’: two squares in a horizontal or vertical direction,
             * then move one square horizontally or vertically.
             * They are the only piece able to jump over other pieces.
             */
            case KNIGHT: {
                return isLMove();
            }

            /*
             * Pawns move vertically forward one square, with the option to move two squares
             * if they have not yet moved. Pawns are the only piece to capture
             * different to how they move. The pawns capture one square
             * diagonally in a forward direction
             */
            case PAWN: {
                if (Objects.equals(computeDirection(), "UP")) {
                    if (isNotChanged(initial)) {  //hasn't been moved
                        if(getDistance() <= 2){
                            return true;
                        }
                    }
                    else{
                        return getDistance() == 1 || isPawnCapture(initial,target);
                    }
                }
            }
        }
        return false;
    }

    public static Position isCheck(Board enemyBoard, Move move){
        Position target = move.getTarget();
        System.out.println(target.print());
        if(enemyBoard.getPieceAtPosition(target) != PieceType.NONE){
            System.out.println(enemyBoard.getPieceAtPosition(target).toString());
            //enemyBoard.replace(new Move(target, target, PieceType.NONE, enemyBoard));
            return target;
        }
        return null;
    }

    public boolean isMoveLegal() {
        if(this.thisBoard.getPieceAtPosition(this.getTarget()) != PieceType.NONE){
            return false;
        }
        switch (pieceType) {
            /*
             * Kings move one square in any direction, so long as that square
             * is not attacked by an enemy piece. Additionally, kings are able
             * to make a special move, known as castling.
             */
            case KING: {
                //+castling = rocada rege cu tura
                return (getDistance() == 1);
            }

            /*
             * Queens move diagonally, horizontally, or vertically
             * any number of squares. They are unable to jump over pieces.
             */
            case QUEEN: {
                return !isJump(thisBoard) && (diagonalDirections.contains(computeDirection()) || (horizVerticalDirections.contains(computeDirection())));
            }

            /*
             * Rooks move horizontally or vertically any number of squares.
             * They are unable to jump over pieces. Rooks move when the king castles.
             */
            case ROOK: {
                return (horizVerticalDirections.contains(computeDirection()) && !isJump(thisBoard));
            }

            /*
             * Bishops move diagonally any number of squares.
             *  They are unable to jump over pieces.
             */
            case BISHOP: {
                return (diagonalDirections.contains(computeDirection()) && !isJump(thisBoard));
            }

            /*
             * Knights move in an ‘L’ shape’: two squares in a horizontal or vertical direction,
             * then move one square horizontally or vertically.
             * They are the only piece able to jump over other pieces.
             */
            case KNIGHT: {
                return isLMove();
            }

            /*
             * Pawns move vertically forward one square, with the option to move two squares
             * if they have not yet moved. Pawns are the only piece to capture
             * different to how they move. The pawns capture one square
             * diagonally in a forward direction
             */
            case PAWN: {

                if (Objects.equals(computeDirection(), "UP")) {
                    if (isNotChanged(initial)) {  //hasn't been moved

                        if(getDistance() <= 2){
                            return true;
                        }
                    }
                    else{
                        return getDistance() == 1 || isPawnCapture(initial,target);
                    }
                }
            }
        }
        return false;
    }

    /**
     * Work in progress
     */
    private int getDistance() {
        char initialLetter = initial.getLetter();
        int initialNumber = initial.getNumber();

        char targetLetter = target.getLetter();
        int targetNumber = target.getNumber();

        if (initialLetter == targetLetter) {
            return Math.abs(initialNumber - targetNumber);
        }

        if (initialNumber == targetNumber) {
            return Math.abs((initialLetter - 'A') - (targetLetter - 'A'));
        }

        return -1;
    }

    private List<Position> getDiagonals(Position position){
        char letter = position.getLetter();
        int number = position.getNumber();
        List<Position> diagonals = new ArrayList<>();
        //x+k, y+k
        //x+k, y-k
        //x-k, y+k
        //x-k, y-k
        for(int k=1;k<=8;k++){
            Position diag = new Position((char) (letter + k), number + k);
            if(legalPosition(diag)){
                diagonals.add(diag);
            }

            diag = new Position((char) (letter + k), number - k);
            if(legalPosition(diag)){
                diagonals.add(diag);
            }

            diag = new Position((char) (letter - k), number + k);
            if(legalPosition(diag)){
                diagonals.add(diag);
            }

            diag = new Position((char) (letter - k), number - k);
            if(legalPosition(diag)){
                diagonals.add(diag);
            }
        }
        return diagonals;
    }

    private boolean legalPosition(Position position){
        return position.getLetter() >= 'A' && position.getLetter() <= 'H' && position.getNumber() >= 1 && position.getNumber() <= 8;
    }

    public String computeDirection() {
        if (initial.getNumber() == target.getNumber()) { //HORIZONTALLY
            if (initial.getLetter() - 'A' < target.getLetter() - 'A') {
                return "RIGHT";
            }
            return "LEFT";
        }

        if (initial.getLetter() == target.getLetter()) { //VERTICALLY
            if (initial.getNumber() < target.getNumber()) {
                return "UP";
            }
            return "DOWN";
        }

        List<Position> diagonals = getDiagonals(initial);
        for(Position p : diagonals){
            if(p.equals(target)){
                if(target.getNumber() - initial.getNumber() > 0 && target.getLetter() - initial.getLetter() > 0){
                    return "RIGHT_UP";
                }
                if(target.getNumber() - initial.getNumber() < 0 && target.getLetter() - initial.getLetter() > 0){
                    return "RIGHT_DOWN";
                }
                if(target.getNumber() - initial.getNumber() > 0 && target.getLetter() - initial.getLetter() < 0){
                    return "LEFT_UP";
                }
                if(target.getNumber() - initial.getNumber() < 0 && target.getLetter() - initial.getLetter() < 0){
                    return "LEFT_DOWN";
                }
            }
        }
//        int delta = initial.getLetter() - target.getLetter();
//        if (delta < 0) {
//            if (initial.getNumber() - target.getNumber() == delta) {
//                return "RIGHT_UP";
//            }
//            if (initial.getNumber() - target.getNumber() == -1 * delta) {
//                return "RIGHT_DOWN";
//            }
//        } else {
//            if (initial.getNumber() - target.getNumber() == delta) {
//                return "LEFT_UP";
//            }
//            if (initial.getNumber() - target.getNumber() == -1 * delta) {
//                return "LEFT_DOWN";
//            }
//        }

        return null;
    }

    /*private boolean isJump(){
        return isJumpOverPiece(player.getBoard());
    }*/

    private boolean isJump(Board board){
        return isJumpOverPiece(board);
    }

    private boolean isJumpOverEnemyPiece(Board board){
        return isJumpOverPiece(board);
    }


    private boolean isJumpOverPiece(Board board) {
        String direction = computeDirection();
        if (direction == null){
            return false;
        }
        switch (direction) {
            case "UP": {
                int initialNumber = initial.getNumber();
                int targetNumber = target.getNumber();

                for (int i = initialNumber + 1; i <= targetNumber; i++) {
                    if (board.getPieceAtPosition(new Position(initial.getLetter(), i)) != PieceType.NONE) {
                        return true;
                    }
                }
                return false;
            }

            case "DOWN": {
                int initialNumber = initial.getNumber();
                int targetNumber = target.getNumber();

                for (int i = targetNumber; i < initialNumber; i++) {
                    if (board.getPieceAtPosition(new Position(initial.getLetter(), i)) != PieceType.NONE) {
                        return true;
                    }
                }
                return false;
            }

            case "RIGHT": {
                char initialLetter = initial.getLetter();
                char targetLetter = target.getLetter();

                for (int i = initialLetter - 'A' + 1; i <= targetLetter - 'A'; i++) {
                    if (board.getPieceAtPosition(new Position((char) (i + 'A'), initial.getNumber())) != PieceType.NONE) {
                        return true;
                    }
                }
                return false;
            }

            case "LEFT": {
                char initialLetter = initial.getLetter();
                char targetLetter = target.getLetter();

                for (int i = targetLetter - 'A'; i < initialLetter - 'A'; i++) {
                    if (board.getPieceAtPosition(new Position((char) (i + 'A'), initial.getNumber())) != PieceType.NONE) {
                        return true;
                    }
                }
                return false;
            }

            default: {
                break;
            }

        }
        if (diagonalDirections.contains(direction)){
            List<Position> positions = computeContainedPositions(initial, target, direction);
            for(Position position : positions){
                System.out.println(position.print());
                if(board.getPieceAtPosition(position) != PieceType.NONE){
                    return true;
                }
            }


        }

        return false;
    }

    public List<Position> computeContainedPositions(Position initial, Position target, String direction){
        char initialLetter = initial.getLetter();
        int initialNumber = initial.getNumber();
        char targetLetter = target.getLetter();

        List<Position> positions = new ArrayList<>();

        switch (direction){
            case "RIGHT_UP":{

                int delta = targetLetter - initialLetter;
                if(delta<0){
                    delta*=-1;
                }
                for(int i=1;i<delta;i++){
                    positions.add(new Position((char) (initialLetter+i), initialNumber+i));
                }
                return positions;
            }

            case "RIGHT_DOWN":{

                int delta = targetLetter - initialLetter;
                if(delta<0){
                    delta*=-1;
                }
                for(int i=1;i<delta;i++){
                    positions.add(new Position((char) (initialLetter+i), initialNumber-i));
                }
                return positions;
            }

            case "LEFT_UP":{

                int delta = targetLetter - initialLetter;
                if(delta<0){
                    delta*=-1;
                }
                for(int i=1;i<delta;i++){
                    positions.add(new Position((char) (initialLetter-i), initialNumber+i));
                }
                return positions;
            }

            case "LEFT_DOWN":{

                int delta = targetLetter - initialLetter;
                if(delta<0){
                    delta*=-1;
                }
                for(int i=1;i<delta;i++){
                    positions.add(new Position((char) (initialLetter-i), initialNumber-i));
                }
                return positions;
            }
        }
        return positions;
    }

    private boolean isLMove() {
        int initialNumber = initial.getNumber();
        char initialLetter = initial.getLetter();

        int targetNumber = target.getNumber();
        char targetLetter = target.getLetter();
        int delta = (initialLetter - targetLetter) * (initialNumber - targetNumber);
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

    private boolean isNotChanged(Position position) {
        return position.getNumber() == 2;
    }

    private boolean isPawnCapture(Position pawnPosition, Position target){
        char letter = pawnPosition.getLetter();
        int number = pawnPosition.getNumber();

        Position enemyPosition1 = new Position((char) (letter-1), number+1);
        Position enemyPosition2 = new Position((char) (letter+1), number+2);

        return target.equals(enemyPosition1) || target.equals(enemyPosition2);
    }

    public void print(){
        System.out.println(getInitial().print() + " " + getTarget().print() + " " + pieceType.toString());
    }
}
