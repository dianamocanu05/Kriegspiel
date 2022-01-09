package org.example.GameLogic;

public class PiecePosition {
    private Position position;
    private PieceType pieceType;
    private String color;

    public PiecePosition(Position position, PieceType pieceType, String color){
        this.pieceType = pieceType;
        this.position = position;
        this.color = color;
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

    public String getColor(){
        return color;
    }

    @Override
    public boolean equals(Object other){
        return (this.getPosition().equals(((PiecePosition) other).getPosition()) && this.getPieceType().toString().equals(((PiecePosition) other).getPieceType().toString()));
    }
}
