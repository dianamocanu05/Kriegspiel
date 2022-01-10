package org.example.GameLogic.Algorithms;

import java.util.ArrayList;
import java.util.List;

public class Node {
    private State state;
    private List<Node> children;
    private int nSampled;
    private int winningScore;//????
    private Node parent = null;
    public Node(State state, Node parent){
        this.state =state;
        this.children = new ArrayList<>();
        this.nSampled = 0;
        this.winningScore = 0;
        this.parent = parent;
    }

    public void addChild(Node child){
        this.children.add(child);
    }
    public boolean isLeafNode(){
        return this.children.size() ==0;
    }

    public List<Node> getChildren(){
        return children;
    }

    public int getnSampled(){
        return nSampled;
    }

    public State getState(){
        return state;
    }

    public int getWinningScore(){
        return winningScore;
    }

    public Node getParent(){
        return parent;
    }
}
