package org.example.GameLogic.Algorithms;

import org.example.GameLogic.Position;

public class Probabilities {

    public static Double opponentControlsPosition(Position position){
        double probability =0;


        return probability;
    }

    public static Double legalPosition(Position position){
        double probability =1;


        return probability;
    }

    //a piece is pinned if moving it would leave the king in check
    public static Double isPinnedPosition(Position position){
        double probability =0;
        /*
        * 0 if the piece is not protecting the king,
         opponentControlsPosition(i1 j1) if the piece is protecting the king,
         opponentControlsPosition(i2 j2) if the piece is the king.
        * */


        return probability;
    }

    public static double opponentMoveKing(int nPawns, int nPieces){
        return (double) 1/(nPawns + nPieces + 1);
    }

    public static double opponentMovePawn(int nPawns, int nPieces){
        return (double) nPawns/(nPawns + nPieces + 1);
    }

    public static double opponentMovePiece(int nPawns, int nPieces){
        return (double) nPieces/(nPawns + nPieces + 1);
    }
}
