package org.example.GameLogic.Players;

import org.example.GameLogic.*;
import org.example.GameLogic.Algorithms.MCTS;
import org.example.GameLogic.Algorithms.Node;
import org.example.GameLogic.Algorithms.State;

public class IntelligentPlayer extends Player{

    public IntelligentPlayer(String name, String color){
        super(name, color);
    }

    @Override
    public Move attemptMove() throws InterruptedException {
        try{
            Thread.sleep(4*1000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        MCTS mcts = new MCTS(new Node(new State(this.board, null),null));
        State state = mcts.run();


        lastMove = state.getCreatorMove();
        return lastMove;
    }

}
