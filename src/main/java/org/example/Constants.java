package org.example;

import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class Constants {
    private static final int HEIGHT = 675;
    private static final int WIDTH = 1200;
    private static final String backgroundImg = "file:media/background.jpg";
    private static final String audio1 = "media/audio1.mp3";
    private static final String audio2 = "";
    private static final Font font = Font.font("Stencil", FontWeight.BOLD, 36);

    public static int getHeight(){
        return HEIGHT;
    }

    public static int getWidth(){
        return WIDTH;
    }


    public static String getBackgroundImg() {
        return backgroundImg;
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
}
