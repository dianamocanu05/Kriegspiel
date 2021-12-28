package org.example.GameLogic.Players;

import org.example.GameLogic.Board;
import org.example.GameLogic.Move;

public class Referee {
    private boolean lastOk;
    public Referee(){};

    public void setLastOk(boolean lastOk){
        this.lastOk = lastOk;
    }
    public String announce(Player currentPlayer, Move attemptedMove){
        if(currentPlayer instanceof HumanPlayer) {
            if (lastOk) {
                return "YES, SIR";
            }
        }else{
            if(attemptedMove.isMoveLegal()){
                return "YES, SIR";
            }
        }
        return "NO, SIR";
    }

    public boolean getLastOk(){return lastOk;}

}
