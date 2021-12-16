package org.example.GameLogic;

import javafx.geometry.Pos;

import java.time.Period;
import java.util.HashMap;
import java.util.Map;

public class Board {
    private HashMap<Position, Piece> configuration;

    public Board() {
        configuration = new HashMap<>();
        initialConfiguration();
    }

    public void putPiece(Piece piece, Position position){
        replace(piece, position);
    }

    private void replace(Piece newPiece, Position position){
        for(Map.Entry<Position, Piece> entry : configuration.entrySet()){
            if(entry.getKey().equals(position)){
                configuration.replace(entry.getKey(), newPiece);
            }
        }
    }

    public HashMap<Position, Piece> getConfiguration(){
        return configuration;
    }

    public Piece getPieceAtPosition(Position position){
        for(Map.Entry<Position, Piece> entry : configuration.entrySet()){
            if(entry.getKey().equals(position)){
                return entry.getValue();
            }
        }
        return null;
    }

    public void initialConfiguration(){

        for(int i=1;i<=8;i++){
            char letter = (char)('A' + i -1);
            Position position = new Position(letter,2);
            this.configuration.put(position, Piece.PAWN);
        }
        this.configuration.put(new Position('A',1),Piece.ROOK);
        this.configuration.put(new Position('B',1),Piece.BISHOP);
        this.configuration.put(new Position('C',1), Piece.KING);
        this.configuration.put(new Position('D',1), Piece.KNIGHT);
        this.configuration.put(new Position('E',1), Piece.BISHOP);
        this.configuration.put(new Position('F',1),Piece.KNIGHT);
        this.configuration.put(new Position('G',1), Piece.ROOK);
        this.configuration.put(new Position('H',1), Piece.QUEEN);
    }




}
