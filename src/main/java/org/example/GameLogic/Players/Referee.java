package org.example.GameLogic.Players;

import org.example.GameLogic.Board;
import org.example.GameLogic.Move;

public class Referee {

    public Referee(){};

    public boolean announce(Player currentPlayer, Move attemptedMove){
        if(attemptedMove.isMoveLegal()){
            System.out.println("YES, SIR");
            return true;
        }
        System.out.println("NO, SIR");
        return false;
    }

}
