package org.example.GameLogic;

import javafx.geometry.Pos;

import java.time.Period;
import java.util.*;

public class Board {
    private final List<PiecePosition> configuration;

    public Board() {
        configuration = new ArrayList<>();
        initialConfiguration();
    }


    public void replace(Move move){
        for(PiecePosition piecePosition : this.configuration){
            if(piecePosition.getPosition().equals(move.getInitial())){
                piecePosition.setPieceType(PieceType.NONE);
            }
            if(piecePosition.getPosition().equals(move.getTarget())){
                piecePosition.setPieceType(move.getPieceType());
            }
        }
        System.out.println("Moved " + move.getPieceType().toString());
    }


    public List<PiecePosition> getConfiguration(){
        return this.configuration;
    }

    public PieceType getPieceAtPosition(Position position){
        for(PiecePosition piecePosition : this.configuration){
            if(piecePosition.getPosition().equals(position)){
                return piecePosition.getPieceType();
            }
        }
        return PieceType.NONE;
    }

    public void initialConfiguration(){

        for(int i=1;i<=8;i++){
            char letter = (char)('A' + i -1);
            Position position = new Position(letter,2);
            this.configuration.add(new PiecePosition(position, PieceType.PAWN, "white"));

            for(int j=3; j<=8; j++){
                this.configuration.add(new PiecePosition(new Position(letter,j), PieceType.NONE, "white") );
            }
        }
        this.configuration.add(new PiecePosition(new Position('A',1),PieceType.ROOK, "white"));
        this.configuration.add(new PiecePosition(new Position('B',1),PieceType.BISHOP, "white"));
        this.configuration.add(new PiecePosition(new Position('C',1), PieceType.KING, "white"));
        this.configuration.add(new PiecePosition(new Position('D',1), PieceType.KNIGHT, "white"));
        this.configuration.add(new PiecePosition(new Position('E',1), PieceType.BISHOP, "white"));
        this.configuration.add(new PiecePosition(new Position('F',1),PieceType.KNIGHT, "white"));
        this.configuration.add(new PiecePosition(new Position('G',1), PieceType.ROOK, "white"));
        this.configuration.add(new PiecePosition(new Position('H',1), PieceType.QUEEN, "white"));
    }

    public void print(){
        int count = 0;
        for(int i=1;i<=8;i++) {
            for (int j = 1; j <= 8; j++) {
                if(getPieceAtPosition(new Position((char)('A' + i -1),j)) !=  PieceType.NONE){
                    count++;
                }
            }
        }
        System.out.println(count);
    }








}
