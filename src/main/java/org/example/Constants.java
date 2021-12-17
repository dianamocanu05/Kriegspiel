package org.example;

import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import org.example.GameLogic.PieceType;

public class Constants {
    private static final int HEIGHT = 675;
    private static final int WIDTH = 1200;
    private static final String backgroundImg = "file:media/background.jpg";
    private static final String aboutImg = "file:media/soldier.jpg";
    private static final String borodinoMap = "file:media/borodino-map.jpg";
    private static final String audio1 = "media/audio1.mp3";
    private static final String audio2 = "media/audio2.mp3";
    private static final Font font = Font.font("Stencil", FontWeight.BOLD, 36);
    private static final Font littleFont = Font.font("Stencil", FontWeight.BOLD, 12);

    public static int getHeight(){
        return HEIGHT;
    }

    public static int getWidth(){
        return WIDTH;
    }


    public static String getBackgroundImg() {
        return backgroundImg;
    }

    public static String getAboutImg() {
        return aboutImg;
    }

    public static String getAudio1() {
        return audio1;
    }

    public static String getAudio2() {
        return audio2;
    }

    public static Font getFont(){
        return font;
    }

    public static Font getLittleFont(){
        return littleFont;
    }

    public static String getBorodinoMap(){
        return borodinoMap;
    }

    public static String getPiecePath(PieceType piece, String color){
        return "file:media/pieces/" + piece.toString() + "_" + color + ".png";
    }
}
