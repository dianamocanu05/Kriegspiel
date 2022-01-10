package org.example.GameLogic.Algorithms;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Node {
    private int state;
    private int winValue = 0;
    private int policyValue = 0;
    private int visits = 0;
    private Node parent = null;
    private List<Node> children = new ArrayList<>();
    private boolean expanded = false;
    private int playerNumber = 0;
    private final  double discoveryFactor = 0.35;

    public Node(int state){
        this.state = state;
    }


    public void updateWinValue(int value){
        this.winValue += value;
        this.visits += 1;
        if(this.parent != null){
            this.parent.updateWinValue(value);
        }
    }

    public void updatePolicyValue(int value){
        this.policyValue = value;
    }

    public void addChild(Node child){
        this.children.add(child);
    }

    public Node getPreferredChild(Node rootNode){
        List<Node> bestChildren = new ArrayList<>();
        double bestScore = -10000;

        for(Node child : this.children){
            double score = child.getScore(rootNode);
            if(score > bestScore){
                bestScore = score;
                bestChildren = new ArrayList<>();
                bestChildren.add(child);
            }else if(score == bestScore){
                bestChildren.add(child);
            }
        }
        return Node.getRandomChoice(bestChildren);
    }

    public double getScore(Node rootNode){
        double discoveryOperand;
        if(this.parent == null){
            discoveryOperand = 0;
        }else {
            discoveryOperand = this.discoveryFactor * (this.policyValue | 1) * Math.sqrt(Math.log(this.parent.getVisits()) / (this.visits | 1));
        }
        int winMultiplier;
        if(this.parent!=null && this.parent.playerNumber == rootNode.getPlayerNumber()){
            winMultiplier = 1;
        }else{
            winMultiplier = -1;
        }
        return winMultiplier + discoveryOperand;
    }

    public Node getParent(){ return this.parent;}
    public int getVisits(){return this.visits;}
    public int getPlayerNumber(){ return this.playerNumber;}
    public List<Node> getChildren(){return  this.children;}
    public boolean getExpanded(){return this.expanded;}
    public void eraseChildren(){this.children = new ArrayList<>();}
    public void setExpanded(boolean expanded){this.expanded =expanded;}
    public boolean isScorable(){return this.visits != 0;} //self.visits or self.policy_value != None
    public int getState(){return this.state;}


    public static Node getRandomChoice(List<Node> nodes){
        int len = nodes.size();
        int random = ThreadLocalRandom.current().nextInt(0,len);
        return nodes.get(random);
    }
}
