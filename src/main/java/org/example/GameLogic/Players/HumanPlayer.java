package org.example.GameLogic.Players;

import org.example.GameLogic.Game;
import org.example.GameLogic.Move;
import org.example.GameLogic.PiecePosition;

public class HumanPlayer extends Player {

    private Move attemptedMove;

    public HumanPlayer(String name, String color) {
        super(name, color);
    }

   @Override
    public Move attemptMove() throws InterruptedException {
        synchronized (game.getGUI().mutex){
            while(!game.getGUI().chosen){
                game.getGUI().mutex.wait();
            }
        }
        lastMove = game.getGUI().lastMove;
        lastMove.print();
        return lastMove;
    }


}
