package org.example.GameLogic.Players;

import org.example.GameLogic.*;

public class IntelligentPlayer extends Player{

    public IntelligentPlayer(String name, String color){
        super(name, color);
    }

    @Override
    public Move attemptMove() throws InterruptedException {
        lastMove = new Move(new Position('A',1),new Position('B',2), PieceType.NONE, new Board(), this);
        return lastMove;
    }

}
