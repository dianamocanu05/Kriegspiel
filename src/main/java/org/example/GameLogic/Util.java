package org.example.GameLogic;

import javafx.scene.image.ImageView;

import java.util.HashMap;
import java.util.List;
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


    public static boolean isEq(List<Image> images, List<PiecePosition> pp){
        for(Image i : images){
            for(PiecePosition p : pp){
                if(i.getPosition().equals(p.getPosition())){
                    if(i.getImageView() == null && !p.getPieceType().equals(PieceType.NONE)){
                        return false;
                    }
                    if(i.getImageView() != null && p.getPieceType().equals(PieceType.NONE)){
                        return false;
                    }
                }
            }
        }
        return true;
    }



}
