package org.example.GameLogic;

import javafx.scene.image.ImageView;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Util {

    public static ImageView getImageView(HashMap<PiecePosition, ImageView> images, PiecePosition piecePosition){
        for(Map.Entry<PiecePosition, ImageView> entry : images.entrySet()){
            PiecePosition position = entry.getKey();
            if(position.equals(piecePosition)){
                return entry.getValue();
            }
        }

        return null;
    }



}
