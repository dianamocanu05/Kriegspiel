package org.example.GameLogic;

public class PiecePosition {
    private Position position;
    private PieceType pieceType;

    public PiecePosition(Position position, PieceType pieceType){
        this.pieceType = pieceType;
        this.position = position;
    }

    public void setPieceType(PieceType pieceType){
        this.pieceType = pieceType;
    }

    public Position getPosition(){
        return position;
    }

    public PieceType getPieceType(){
        return pieceType;
    }
}
