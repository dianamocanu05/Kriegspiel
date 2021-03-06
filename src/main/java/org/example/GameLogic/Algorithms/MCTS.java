package org.example.GameLogic.Algorithms;

import org.example.GameLogic.Move;
import org.example.GameLogic.Players.Referee;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class MCTS {
    //1. tree traversal using ucb1
    //2. node expansion
    //3. rollout (random simulation)
    //4. backpropagation

    private Node root;
    private Move lastMove = null;
    public MCTS(Node root){
        this.root = root;
    }

    public State run(){
        return treeTraversal();
    }

    public State treeTraversal(){
        Node currentNode = root;
        currentNode.incrementNSample();
        State state = null;
        while(!currentNode.isLeafNode()){
            currentNode = getBestChild(currentNode);
        }

        if(currentNode.getnSampled() == 0){
             state = rollout(currentNode.getState());
        }else{
            List<Move> possibleActions = currentNode.getState().getAvailableActions();
            for(Move action : possibleActions){

                State newState = Utils.makeAction(currentNode.getState(), action);

                Node newNode = new Node(newState, currentNode);
                currentNode.addChild(newNode);
            }
            currentNode.setWinningScore(evaluator(currentNode.getState()));
            backpropagate(currentNode);
            currentNode = currentNode.getChildren().get(0);
            currentNode.incrementNSample();
            state = rollout(currentNode.getState());
        }
        return state;
    }

    public State rollout(State state){
        if(state.isTerminal()){
            return state;
        }

        Move action = MCTS.getRandomAction(state.getAvailableActions());

        return simulate(state, action);
    }

    public State simulate(State state, Move action){
        lastMove = action;
        return Utils.makeAction(state, action);
    }

    public void backpropagate(Node node){
        Node parent = node.getParent();
        while(parent != null){
            parent.setWinningScore(node.getWinningScore());
            parent = parent.getParent();
        }
    }



    private Node getBestChild(Node node){
        List<Node> children = node.getChildren();
        double maxUCB = - 1000;
        Node bestChild = null;
        for(Node child : children){
            double ucb1 = MCTS.ucb1(child);
            if(ucb1 > maxUCB){
                maxUCB = ucb1;
                bestChild = child;
            }
        }
        return bestChild;
    }

    public Move getLastMove(){
        return lastMove;
    }

    //upper confidence bound 1 formula
    private static double ucb1(Node node){
        double log = Math.log(node.getParent().getnSampled())/Math.log(Math.E);
        return node.getWinningScore() + 2 * (Math.sqrt(log/node.getnSampled()));
    }

    public static Move getRandomAction(List<Move> actions){
        int len = actions.size();
        return actions.get(ThreadLocalRandom.current().nextInt(0, len));
    }

    //rewards
    public int evaluator(State state){

        if(state.getCreatorMove() == null){
            return 0;
        }
        Referee referee = new Referee();
        String feedback = referee.announce(state, state.getCreatorMove());
        if(feedback.equals("CHECK")){
            return +1000;
        }else if(feedback.equals("CAPTURE")){
            return +500;
        }else{
            return -10;
        }
    }


}
