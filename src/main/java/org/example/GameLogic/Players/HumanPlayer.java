package org.example.GameLogic.Players;

import javafx.scene.image.ImageView;
import org.example.GameLogic.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Human Player
 */
public class HumanPlayer extends Player {
    private List<Image> images= new ArrayList<>();
    private Move attemptedMove;

    public HumanPlayer(String name, String color) {
        super(name, color);
    }

   @Override
    public Move attemptMove() throws InterruptedException {
        synchronized (game.getGUI().mutex){
            while(!game.getGUI().chosen){
                game.getGUI().mutex.wait();
            }
        }
        lastMove = game.getGUI().lastMove;
        lastMove.print();
        return lastMove;
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


}
