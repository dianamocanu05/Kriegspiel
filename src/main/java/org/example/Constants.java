package org.example;

import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import org.example.GameLogic.PieceType;

public class Constants {
    //ENV
    private static final int HEIGHT = 675;
    private static final int WIDTH = 1200;

    //IMAGES
    private static final String backgroundImg = "file:media/background.jpg";
    private static final String aboutImg = "file:media/soldier.jpg";
    private static final String speechBubbleImg = "file:media/speech.png";
    private static final String butlerImg = "file:media/butler.png";
    private static final String borodinoMap = "file:media/borodino-map.jpg";

    //MUSIC
    private static final String audio1 = "media/audio1.mp3";
    private static final String audio2 = "media/audio2.mp3";

    //FONTS
    private static final Font font = Font.font("Stencil", FontWeight.BOLD, 36);
    private static final Font littleFont = Font.font("Stencil", FontWeight.BOLD, 12);
    private static final String stringFont = "-fx-font: 36 Stencil";
    private static final String butlerStringFont = "-fx-font: 24 Stencil";



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
    public static String getStringFont(){return stringFont;}
    public static String getButlerStringFont(){return butlerStringFont;}
    public static String getBorodinoMap(){
        return borodinoMap;
    }
    public static String getPiecePath(PieceType piece, String color){
        return "file:media/pieces/" + piece.toString() + "_" + color + ".png";
    }
    public static String getSpeechBubbleImg(){ return speechBubbleImg;}
    public static String getButlerImg(){ return butlerImg;}
}
