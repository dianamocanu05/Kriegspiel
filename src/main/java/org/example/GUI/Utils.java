package org.example.GUI;

import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.scene.Group;
import javafx.scene.ImageCursor;
import javafx.scene.Node;
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
    public static Text addNameText(String name, Group group) {
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
        group.setTranslateX(0);
        group.setTranslateY((float) -1 * Constants.getWidth() / 4);
        return text;
    }

    public static void removeNameText(Text text, Group group) {
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

    public static void addButler(StackPane stackPane) {
        Image image = new Image(Constants.getButlerImg());
        ImageView imageView = new ImageView(image);
        //340 x 750
        imageView.setFitWidth(100);
        imageView.setFitHeight(200);
        imageView.setTranslateX((float) -1 * Constants.getWidth() / 2 + 100);
        imageView.setTranslateY((float) Constants.getHeight() / 2 - 100);

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                stackPane.getChildren().add(imageView);
            }
        });
    }

    public static Text addButlerMessage(Group group, String announce) {
        Text bubbleView = new Text();
        bubbleView.setText(announce);
        bubbleView.setStyle(Constants.getButlerStringFont());
        bubbleView.setFill(Color.RED);
        bubbleView.setTranslateX((float) -1 * Constants.getWidth() / 2 + 140);
        bubbleView.setTranslateY((float) Constants.getHeight() / 2 - 120);

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                group.getChildren().add(bubbleView);
            }
        });
        return bubbleView;
    }

    public static void removeButlerMessage(StackPane stackPane, Text message) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                stackPane.getChildren().remove(message);

            }
        });
    }


}
