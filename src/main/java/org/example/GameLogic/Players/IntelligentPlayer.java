package org.example.GameLogic.Players;

import org.example.GameLogic.*;

public class IntelligentPlayer extends Player{

    public IntelligentPlayer(String name, String color){
        super(name, color);
    }

    @Override
    public Move attemptMove() throws InterruptedException {
        try{
            Thread.sleep(2*1000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        lastMove = new Move(new Position('A',2),new Position('A',3), PieceType.PAWN, new Board("AvA"),getOpponent().getBoard(), this);
        return lastMove;
    }

}
