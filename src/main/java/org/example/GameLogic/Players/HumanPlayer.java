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
    public Move attemptMove() {
        Move move;
        synchronized (game.getGUI().mutex){
            while(!game.getGUI().chosen){
                try{
                    game.getGUI().mutex.wait();
                }catch (InterruptedException exc){
                    exc.printStackTrace();
                }
            }
        }
        move = game.getGUI().lastMove;
        return move;
    }


}
