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
import javafx.scene.control.Tooltip;
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
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.example.Constants;
import org.example.GameLogic.Logger;
import org.example.GameLogic.Position;

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

    public static ScrollPane addHistory(StackPane stackPane){
        Image image = new Image(Constants.getBooksImg());
        ImageView imageView = new ImageView(image);
        //299 x 410
        imageView.setFitWidth(75);
        imageView.setFitHeight(90);
        imageView.setTranslateX((float)  -1* Constants.getWidth() / 2 + 100);
        imageView.setTranslateY((float) -1 * Constants.getHeight() / 2 + 50);

        Stage history = new Stage();
        ScrollPane scrollPane = new ScrollPane();
        imageView.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                history.setX(300); history.setY(300);
                history.setTitle("War chronicle");
                history.setWidth(300);
                history.setHeight(500);
                StackPane sp = new StackPane();

                scrollPane.setStyle("-fx-background: #FACF7F; -fx-border-color: #FACF7F;");
                //scrollPane.setStyle("-fx-background-image: url('" +Constants.getOldPaperImg()+ "');");
                sp.getChildren().add(scrollPane);
                Scene scene = new Scene(sp);
                history.setScene(scene);
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
        return scrollPane;
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


    public static void updateHistory(ScrollPane scrollPane, Logger logger){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                scrollPane.setContent(new Text(logger.getLogs()));
            }
        });
    }

    public static void addTooltip(Rectangle rectangle){
        Tooltip tp = new Tooltip("info");
        tp.setStyle(Constants.getLittleStringFont());
        final double[] x = new double[1];
        final double[] y = new double[1];

        rectangle.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                x[0] = mouseEvent.getScreenX();
                y[0] = mouseEvent.getScreenY();
            }
        });

        rectangle.setOnMouseMoved(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Tooltip.install(rectangle,tp);
                tp.setShowDuration(new Duration(100));
                tp.show(rectangle, x[0], y[0]);

            }
        });

        rectangle.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                tp.hide();
                Tooltip.uninstall(rectangle,tp);
            }
        });
    }





}
