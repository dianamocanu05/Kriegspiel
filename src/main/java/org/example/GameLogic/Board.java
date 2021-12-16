package org.example.GameLogic;

import javafx.geometry.Pos;

import java.util.HashMap;
import java.util.Map;

public class Board {
    private HashMap<Position, Piece> configuration;

    public Board() {
        configuration = new HashMap<>();
        for (int i = 1; i <= 8; i++) {
            Position position = new Position((char) ('A' + i - 1), i);
            configuration.put(position, null);
        }
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




}
