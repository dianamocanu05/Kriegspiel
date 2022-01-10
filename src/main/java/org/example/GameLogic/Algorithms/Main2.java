package org.example.GameLogic.Algorithms;

import org.example.GameLogic.Board;

public class Main2 {

    public static void main(String[] args){
        Board board = new Board("DOWN");
        MCTS mcts = new MCTS(new Node(new State(board),null));
        State state = mcts.run();
    }
}
