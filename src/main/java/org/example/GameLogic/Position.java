package org.example.GameLogic;

import javafx.geometry.Pos;

public class Position {
    private char letter;
    private int number;

    public Position(char letter, int number){
        this.letter = letter;
        this.number = number;
    }

    private char getLetter(){
        return letter;
    }

    private int getNumber(){
        return number;
    }

    @Override
    public boolean equals(Object other){
        if(other == this){
            return true;
        }
        if(!(other instanceof Position)){
            return false;
        }
        return ((Position) other).getLetter() == this.letter && ((Position) other).getNumber() == this.number;
    }
}
