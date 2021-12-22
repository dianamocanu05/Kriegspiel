package org.example.GameLogic.Players;

import org.example.GameLogic.Board;
import org.example.GameLogic.Move;

public class Referee {

    public Referee(){};

    public String announce(Player currentPlayer, Move attemptedMove){
        System.out.print("Referee said to " + currentPlayer.getName() + ":");
        if(attemptedMove.isMoveLegal()){
            return "YES, SIR";
        }
        return "NO, SIR";
    }

}
