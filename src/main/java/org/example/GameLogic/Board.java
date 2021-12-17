package org.example.GameLogic;

import javafx.geometry.Pos;

import java.time.Period;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class Board {
    private final HashMap<Position, PieceType> configuration;

    public Board() {
        configuration = new HashMap<>();
        initialConfiguration();
    }


    public void replace(PieceType newPieceType, Position oldPosition, Position newPosition){
        for(Map.Entry<Position, PieceType> entry : configuration.entrySet()){
            if(entry.getKey().equals(newPosition)){
                configuration.replace(entry.getKey(), newPieceType);
            }
        }
    }


    public HashMap<Position, PieceType> getConfiguration(){
        return configuration;
    }

    public PieceType getPieceAtPosition(Position position){
        for(Map.Entry<Position, PieceType> entry : this.configuration.entrySet()){
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
            this.configuration.put(position, PieceType.PAWN);

            for(int j=3; j<=8; j++){
                this.configuration.put(new Position(letter,j), null);
            }
        }
        this.configuration.put(new Position('A',1),PieceType.ROOK);
        this.configuration.put(new Position('B',1),PieceType.BISHOP);
        this.configuration.put(new Position('C',1), PieceType.KING);
        this.configuration.put(new Position('D',1), PieceType.KNIGHT);
        this.configuration.put(new Position('E',1), PieceType.BISHOP);
        this.configuration.put(new Position('F',1),PieceType.KNIGHT);
        this.configuration.put(new Position('G',1), PieceType.ROOK);
        this.configuration.put(new Position('H',1), PieceType.QUEEN);
    }

    public void print(){
        for(int i=1;i<=8;i++) {
            for (int j = 1; j <= 8; j++) {
                char letter = (char) ('A' + i - 1);
                int number = j;
                System.out.println(letter + "" + number + " - " + getPieceAtPosition(new Position(letter, number)));
            }
        }
    }






}
