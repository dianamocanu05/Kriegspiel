package org.example.GUI;

import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.ImageCursor;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.effect.BlendMode;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.example.Constants;

import java.io.File;

public class Utils {
    public static Text addNameText(String name, StackPane stackPane) {
        Text text = new Text();
        text.setText(name + "'S TURN");
        text.setStyle(Constants.getStringFont());
        text.setFill(Color.RED);

        text.setTranslateX(0);
        text.setTranslateY((float) -1 * Constants.getWidth() / 4);
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                stackPane.getChildren().add(text);
            }
        });

        return text;
    }

    public static void removeNameText(Text text, StackPane stackPane) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                stackPane.getChildren().remove(text);
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

    public static void addHistory(StackPane stackPane){
        Image image = new Image(Constants.getBooksImg());
        ImageView imageView = new ImageView(image);
        //299 x 410
        imageView.setFitWidth(75);
        imageView.setFitHeight(90);
        imageView.setTranslateX((float)  -1* Constants.getWidth() / 2 + 100);
        imageView.setTranslateY((float) -1 * Constants.getHeight() / 2 + 50);

        Stage history = new Stage();
        imageView.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                history.setX(0); history.setX(0);
                history.setTitle("Previous orders");
                history.setScene(createHistoryLogs());
                history.show();
            }
        });

        imageView.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                history.close();
            }
        });
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                stackPane.getChildren().add(imageView);
            }
        });
    }


    public static Text addButlerMessage(StackPane stackPane, String announce) {
        Text bubbleView = new Text();
        bubbleView.setText(announce);
        bubbleView.setStyle(Constants.getButlerStringFont());
        bubbleView.setFill(Color.RED);
        bubbleView.setTranslateX((float) -1 * Constants.getWidth() / 2 + 160);
        bubbleView.setTranslateY((float) Constants.getHeight() / 2 - 100);
        System.out.println(announce);
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                stackPane.getChildren().add(bubbleView);
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

    private static Scene createHistoryLogs(){
        ScrollPane scrollPane = new ScrollPane();
        ImageView imageView = new ImageView(new Image(Constants.getOldPaperImg()));
        imageView.setFitHeight(500);
        imageView.setFitWidth(300);
        scrollPane.setContent(imageView);
        return new Scene(scrollPane);
    }





}
