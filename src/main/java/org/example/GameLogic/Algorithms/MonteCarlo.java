package org.example.GameLogic.Algorithms;

import java.util.*;

public class MonteCarlo {
    private Node rootNode;

    public MonteCarlo(Node rootNode) {
        this.rootNode = rootNode;
    }

    public Node makeChoice() {
        List<Node> bestChildren = new ArrayList<>();
        int mostVisits = -1000;
        List<Node> children = this.rootNode.getChildren();
        for (Node child : children) {
            if (child.getVisits() > mostVisits) {
                mostVisits = child.getVisits();
                bestChildren = new ArrayList<>();
                bestChildren.add(child);
            } else if (child.getVisits() == mostVisits) {
                bestChildren.add(child);
            }
        }
        return Node.getRandomChoice(bestChildren);
    }

    public Node makeExploratoryChoice() {
        HashMap<Node, Integer> childrenVisits = new HashMap<>();
        List<Node> children = this.rootNode.getChildren();
        for (Node child : children) {
            childrenVisits.put(child, child.getVisits());
        }
        List<Double> childrenVisitProbabilities = new ArrayList<>();
        for (Map.Entry<Node, Integer> entry : childrenVisits.entrySet()) {
            if (this.rootNode.getVisits() == 0) {
                childrenVisitProbabilities.add(0.0);
            } else {
                childrenVisitProbabilities.add((double) (entry.getValue() / this.rootNode.getVisits()));
            }
        }
        double randomProbability = new Random().nextDouble() % 1;
        int probCounted = 0;

        for (int i = 0; i < childrenVisitProbabilities.size(); i++) {
            Double probability = childrenVisitProbabilities.get(i);
            if (probCounted + probability >= randomProbability) {
                return this.rootNode.getChildren().get(i);
            }
            probCounted += probability;
        }
        return null;
    }

    public void simulate(int expansionCount) {
        for (int i = 0; i < expansionCount; i++) {
            Node currentNode = this.rootNode;
            while (currentNode.getExpanded()) {
                currentNode = currentNode.getPreferredChild(this.rootNode);
            }
            this.expand(currentNode);
        }
    }

    public void expand(Node node) {
        MonteCarlo.childFinder(node, this);
        List<Node> children = node.getChildren();
        for (Node child : children) {
            int childWinValue = MonteCarlo.nodeEvaluator(child, this);
            if (childWinValue != 0) {
                child.updateWinValue(childWinValue);
            }
            if (!child.isScorable()) {
                randomRollout(child);
                child.eraseChildren();
            }
        }
        if (node.getChildren().size() != 0) {
            node.setExpanded(true);
        }

    }

    public void randomRollout(Node node) {
        MonteCarlo.childFinder(node, this);
        Node child = Node.getRandomChoice(node.getChildren());
        node.eraseChildren();
        node.addChild(child);
        int childWinValue = MonteCarlo.nodeEvaluator(child, this);
        if (childWinValue != 0) {
            node.updateWinValue(childWinValue);
        } else {
            randomRollout(child);
        }
    }

    public static int nodeEvaluator(Node node, MonteCarlo mcts) {
        if (node.getState() > 1000) {
            return 1;
        } else if (node.getState() < -1000) {
            return -1;
        }
        return 0;
    }

    public static void childFinder(Node node, MonteCarlo mcts) {
        if (node.getState() == 0) {
            node.addChild(new Node(1));
            node.addChild(new Node(-1));
        } else {
            for (int i = 0; i < 2; i++) {
                int modifier;
                if (i == 1) {
                    modifier = 100;
                } else {
                    modifier = 200;
                }
                if (node.getState() < 0) {
                    modifier *= -1;
                }

                node.addChild(new Node(node.getState() + modifier));
            }
        }
    }
}
