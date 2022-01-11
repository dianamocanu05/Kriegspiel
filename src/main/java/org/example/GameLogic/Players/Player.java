package org.example.GameLogic.Players;

import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import org.example.GameLogic.*;

import java.lang.reflect.Array;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

public abstract class Player implements Runnable {

    private String name;
    private String color;
    protected Board board;
    protected Game game;
    private Thread thread;
    protected Move lastMove;
    private Player opponent;
    private List<Image> images= new ArrayList<>();

    public Player(String name, String color) {
        this.name = name;
        this.color = color;
    }

    public void addImage(Position piecePosition, ImageView imageView){
        this.images.add(new Image(piecePosition,imageView));
    }

    public void replaceImage(Position piecePosition, ImageView imageView){
        for(Image image : this.images){
            int index = this.images.indexOf(image);
            if(image.getPosition().equals(piecePosition)){
                image.setImageView(imageView);
                this.images.set(index, image);
            }
        }
    }

    public void eraseImage(Position piecePosition){
        replaceImage(piecePosition, new ImageView());
    }

    public ImageView getImageAtPosition(Position position){
        for(Image image : images){
            if(image.getPosition().equals(position)){
                return image.getImageView();
            }
        }
        return null;
    }

    public List<Image> getImages(){
        return images;
    }

    public void update(Move move){

    }


    public Move getLastMove() {
        return lastMove;
    }

    public void setOpponent(Player opponent) {
        this.opponent = opponent;
    }

    public Player getOpponent(){return  opponent;}

    public abstract Move attemptMove() throws InterruptedException;

    public void setBoard(Board board) {
        this.board = board;
    }


    public void setGame(Game game) {
        this.game = game;
    }

    public Game getGame() {
        return game;
    }

    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }

    public Board getBoard() {
        return board;
    }


    private void waitTurn() throws InterruptedException {
        synchronized (game) {

            while (!game.getCurrentPlayer().equals(this)) {
                this.game.wait();
            }

        }

    }

    public void setThread(Thread thread) {
        this.thread = thread;
    }


    @Override
    public void run() {
        while (true) {
            try {
                waitTurn();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(this.getName() + "'S TURN");

            Move move = null;
            try {
                move = attemptMove();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (this instanceof HumanPlayer) {
                game.getGUI().chosen = false;
            }

            try {
                game.update();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (game) {
                this.game.notify();
            }

        }
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Player)) {
            return false;
        }
        return this.name.equals(((Player) other).getName());
    }


}
