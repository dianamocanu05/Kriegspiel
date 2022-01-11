package org.example.GameLogic;

import javafx.scene.image.ImageView;

/**
 * Image represents the image of a chess piece mapped to it's corresponding current position
 */
public class Image {
    private Position position;
    private ImageView imageView;

    public Image(Position position, ImageView imageView){
        this.imageView = imageView;
        this.position = position;
    }

    public void setImageView(ImageView imageView){
        this.imageView = imageView;
    }

    public ImageView getImageView(){
        return imageView;
    }

    public Position getPosition(){
        return position;
    }

}
