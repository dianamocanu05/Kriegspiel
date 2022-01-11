package org.example.GameLogic;

import javafx.geometry.Pos;

import java.time.Period;
import java.util.*;

public class Board {
    private List<PiecePosition> configurationDOWN = new ArrayList<>();
    private List<PiecePosition> configurationUP = new ArrayList<>();
    private List<PiecePosition> configuration;
    private String position;
    public Board(String position) {
        this.position = position;
        if(position.equals("DOWN")) {
            initialConfiguration();
            configuration = configurationDOWN;
        }else if(position.equals("UP")){
            initialConfigurationUp();
            configuration = configurationUP;
        }
    }

    public String getPosition(){
        return position;
    }


    public Board replace(Move move){
        Board newBoard = new Board(position);
        List<PiecePosition> newConf = new ArrayList<>();
        for(PiecePosition piecePosition : this.configuration){
            if(piecePosition.getPosition().equals(move.getInitial())){
                piecePosition.setPieceType(PieceType.NONE);
            }
            if(piecePosition.getPosition().equals(move.getTarget())){
                piecePosition.setPieceType(move.getPieceType());
            }
            newConf.add(piecePosition);

        }
        newBoard.setConfiguration(newConf);
        return newBoard;
    }

    public Board delete(PiecePosition piecePosition){
        List<PiecePosition> newConfiguration = new ArrayList<>();
        Position position = piecePosition.getPosition();
        for(PiecePosition pos : this.configuration){
            if(piecePosition.equals(pos)){
               pos = new PiecePosition(position, PieceType.NONE, piecePosition.getColor());
            }
            newConfiguration.add(pos);
        }
        Board newBoard = new Board(this.position);
        newBoard.setConfiguration(newConfiguration);
        return newBoard;
    }



    public void setConfiguration(List<PiecePosition> newBoard){
        this.configuration = newBoard;
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
            this.configurationDOWN.add(new PiecePosition(position, PieceType.PAWN, "white"));

            for(int j=3; j<=8; j++){
                this.configurationDOWN.add(new PiecePosition(new Position(letter,j), PieceType.NONE, "white") );
            }
        }
        this.configurationDOWN.add(new PiecePosition(new Position('A',1),PieceType.ROOK, "white"));
        this.configurationDOWN.add(new PiecePosition(new Position('B',1),PieceType.KNIGHT, "white"));
        this.configurationDOWN.add(new PiecePosition(new Position('C',1), PieceType.BISHOP, "white"));
        this.configurationDOWN.add(new PiecePosition(new Position('D',1), PieceType.QUEEN, "white"));
        this.configurationDOWN.add(new PiecePosition(new Position('E',1), PieceType.KING, "white"));
        this.configurationDOWN.add(new PiecePosition(new Position('F',1),PieceType.BISHOP, "white"));
        this.configurationDOWN.add(new PiecePosition(new Position('G',1), PieceType.KNIGHT, "white"));
        this.configurationDOWN.add(new PiecePosition(new Position('H',1), PieceType.ROOK, "white"));
    }
    public void initialConfigurationUp(){
        for(int i=1;i<=8;i++){
            char letter = (char)('A' + i -1);
            Position position = new Position(letter,7);
            this.configurationUP.add(new PiecePosition(position, PieceType.PAWN, "black"));

            for(int j=1; j<=6; j++){
                this.configurationUP.add(new PiecePosition(new Position(letter,j), PieceType.NONE, "black") );
            }
        }
        this.configurationUP.add(new PiecePosition(new Position('A',8),PieceType.ROOK, "black"));
        this.configurationUP.add(new PiecePosition(new Position('B',8),PieceType.KNIGHT, "black"));
        this.configurationUP.add(new PiecePosition(new Position('C',8), PieceType.BISHOP, "black"));
        this.configurationUP.add(new PiecePosition(new Position('D',8), PieceType.QUEEN, "black"));
        this.configurationUP.add(new PiecePosition(new Position('E',8), PieceType.KING, "black"));
        this.configurationUP.add(new PiecePosition(new Position('F',8),PieceType.BISHOP, "black"));
        this.configurationUP.add(new PiecePosition(new Position('G',8), PieceType.KNIGHT, "black"));
        this.configurationUP.add(new PiecePosition(new Position('H',8), PieceType.ROOK, "black"));
    }

    public void print() {
        for (int i = 1; i <= 8; i++) {
            for (int j = 1; j <= 8; j++) {
                System.out.println(getPieceAtPosition(new Position((char) ('A' + i - 1), j)));
            }
        }
    }

    public static List<PiecePosition> getInitialConfiguration(){
        List<PiecePosition> conf = new ArrayList<>();
        for(int i=1;i<=8;i++){
            char letter = (char)('A' + i -1);
            Position position = new Position(letter,2);
            conf.add(new PiecePosition(position, PieceType.PAWN, "white"));

            for(int j=3; j<=8; j++){
                conf.add(new PiecePosition(new Position(letter,j), PieceType.NONE, "white") );
            }
        }
       conf.add(new PiecePosition(new Position('A',1),PieceType.ROOK, "white"));
        conf.add(new PiecePosition(new Position('B',1),PieceType.KNIGHT, "white"));
        conf.add(new PiecePosition(new Position('C',1), PieceType.BISHOP, "white"));
        conf.add(new PiecePosition(new Position('D',1), PieceType.QUEEN, "white"));
        conf.add(new PiecePosition(new Position('E',1), PieceType.KING, "white"));
        conf.add(new PiecePosition(new Position('F',1),PieceType.BISHOP, "white"));
       conf.add(new PiecePosition(new Position('G',1), PieceType.KNIGHT, "white"));
       conf.add(new PiecePosition(new Position('H',1), PieceType.ROOK, "white"));

       return conf;
    }
    public boolean isInitial(){
        List<PiecePosition> initial = getInitialConfiguration();
        for(PiecePosition piecePosition : initial){
            if(!piecePosition.getPieceType().equals(getPieceAtPosition(piecePosition.getPosition()))){
                return false;
            }
        }
        return true;
    }








}
