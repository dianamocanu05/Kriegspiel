package org.example.GUI;

import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import org.example.Constants;

import java.io.File;

public class Utils {
    public static Text addNameText(String name, Group group){
        Text text = new Text();
        text.setText(name + "'S TURN");
        text.setStyle(Constants.getStringFont());
        text.setFill(Color.RED);
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                group.getChildren().add(text);
            }
        });
        group.setTranslateX(0); group.setTranslateY((float) -1*Constants.getWidth()/4);
        return text;
    }

    public static void removeNameText(Text text,Group group){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                group.getChildren().remove(text);
            }
        });
    }

    public static void addImage(StackPane stackPane, String imgPath) {
        Image image = new Image(imgPath);
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(Constants.getHeight());
        imageView.setFitWidth(Constants.getWidth());
        stackPane.getChildren().add(imageView);
    }

    public static void playMusic(StackPane stackPane, String audioPath) {
        MediaView view = new MediaView();
        Media soundtrack = new Media(new File(audioPath).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(soundtrack);
        view.setMediaPlayer(mediaPlayer);
        stackPane.getChildren().add(view);
        mediaPlayer.play();
    }
}
