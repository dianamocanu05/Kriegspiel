package org.example.GameLogic.Players;

import org.example.GameLogic.Board;
import org.example.GameLogic.Game;
import org.example.GameLogic.Move;
import org.example.GameLogic.Position;

public class Referee {
    private boolean lastOk;
    public Referee(Game game){
        this.game = game;
    };
    private Game game;
    public void setLastOk(boolean lastOk){
        this.lastOk = lastOk;
    }
    public String announce(Player currentPlayer, Move attemptedMove){
        if(currentPlayer instanceof HumanPlayer) {
            Position position = Move.isCheck(game.getOtherPlayer(currentPlayer).getBoard(),attemptedMove);
            if(position != null){
                return "CAPTURE AT " + position.print();
            }
            if (lastOk) {
                return "YES, SIR";
            }

        }else{
            Position position = Move.isCheck(game.getOtherPlayer(currentPlayer).getBoard(),attemptedMove);

            if(position != null){
                return "CAPTURE AT " + position.print();
            }
            if(attemptedMove.refisMoveLegal()){
                return "YES, SIR";
            }
        }

        return "NO, SIR";
    }


    public boolean getLastOk(){return lastOk;}

}
