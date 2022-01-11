package org.example.GameLogic.Algorithms;

import org.example.GameLogic.Board;
import org.example.GameLogic.Move;
import org.example.GameLogic.PieceType;
import org.example.GameLogic.Players.IntelligentPlayer;
import org.example.GameLogic.Players.Player;
import org.example.GameLogic.Players.Referee;
import org.example.GameLogic.Position;

import java.util.List;

public class Main2 {

    public static void main(String[] args){
    Board board = new Board("DOWN");
    Move move = new Move(new Position('A',8), new Position('D',5), PieceType.QUEEN, board);
    List<Position> c = move.computeContainedPositions(move.getInitial(), move.getTarget(), move.computeDirection());
    for(Position position : c){
        System.out.println(position.print());
    }
    }
}
