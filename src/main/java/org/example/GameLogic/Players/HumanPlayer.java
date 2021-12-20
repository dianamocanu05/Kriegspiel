package org.example.GameLogic.Players;

import org.example.GameLogic.Game;
import org.example.GameLogic.Move;
import org.example.GameLogic.PiecePosition;

public class HumanPlayer extends Player {

    private Move attemptedMove;

    public HumanPlayer(String name, String color, Game game) {
        super(name, color, game);
    }

    @Override
    public void setAttemptedMove(Move move){
        this.attemptedMove = move;
    }

//    public void waitTurn(){
//        synchronized (this.game.getGameInterface().mutex){
//            while (!game.getGameInterface().chosen) {
//                try {
//                    game.getGameInterface().mutex.wait();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//        System.out.println(attemptedMove.getPieceType().toString());
//    }

    @Override
    public Move chooseMove() {
        Move move;
        synchronized (this.game.getGameInterface().mutex) {
            while (!game.getGameInterface().chosen) {
                try {
                    game.getGameInterface().mutex.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        move = game.getGameInterface().lastMove;
        System.out.println(attemptedMove.getPieceType().toString());
        return move;
    }

}
