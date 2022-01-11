package org.example.GameLogic.Players;

import org.example.GameLogic.*;
import org.example.GameLogic.Algorithms.State;

/**
 * Referee checks the validity of moves and sends feedback
 */
public class Referee {
    private boolean lastOk;
    private State state;
    public Referee(Game game){
        this.game = game;
    };
    public Referee(){

    }
    private Game game;
    public void setLastOk(boolean lastOk){
        this.lastOk = lastOk;
    }


    public String announce(Player currentPlayer, Move attemptedMove){
        if(currentPlayer instanceof HumanPlayer) {
            Position position = Move.isCheck(game.getOtherPlayer(currentPlayer).getBoard(),attemptedMove);
            if(position != null){
                if(attemptedMove.getPieceType().equals(PieceType.KING)){
                    return "CHECK";
                }
                return "CAPTURE AT " + position.print();
            }
            if (lastOk) {
                return "YES, SIR";
            }

        }else{
            Position position = Move.isCheck(game.getOtherPlayer(currentPlayer).getBoard(),attemptedMove);

            if(position != null){
                if(attemptedMove.getPieceType().equals(PieceType.KING)){
                    return "CHECK";
                }
                return "CAPTURE AT " + position.print();
            }
            if(attemptedMove.refisMoveLegal()){
                System.out.println(currentPlayer.getBoard().getPieceAtPosition(attemptedMove.getTarget()).toString());
                return "YES, SIR";
            }
        }

        return "NO, SIR";
    }

    public String announce(State state, Move attemptedMove) {
        Position position = Move.isCheck(state.getOpponentBoard(), attemptedMove);

        if (position != null) {
            if(attemptedMove.getPieceType().equals(PieceType.KING)){
                return "CHECK";
            }else{
                return "CAPTURE";
            }
        }
        return "NOTHING";
    }


    public boolean getLastOk(){return lastOk;}

}
