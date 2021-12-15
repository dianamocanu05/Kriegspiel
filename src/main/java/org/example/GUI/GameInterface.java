package org.example.GUI;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.io.File;
import org.example.Constants;

public class GameInterface {
    private StackPane stackPane;
    private Stage stage;

    public GameInterface(Stage stage){
        this.stage = stage;
    }
    public void initialize(){
        initScreen();
    }

    public void initScreen(){
        stackPane = new StackPane();
        playMusic(stackPane, Constants.getAudio1());
        addImage(stackPane, Constants.getBackgroundImg());
        VBox vbox = new VBox();
        vbox.setSpacing(10);
        vbox.setAlignment(Pos.BOTTOM_CENTER);

        Button play = addButton(stackPane,"Play",stage);
        Button help = addButton(stackPane,  "Help",stage);
        Button about = addButton(stackPane, "About",stage);

        vbox.getChildren().addAll(play,help,about);

        stackPane.getChildren().add(vbox);
        stage.setTitle("Kriegspiel");
        Scene scene = new Scene(stackPane);
        stage.setScene(scene);
        stage.show();
    }

    public static void addImage(StackPane stackPane, String imgPath){
        Image image = new Image(imgPath);
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(Constants.getHeight());
        imageView.setFitWidth(Constants.getWidth());
        stackPane.getChildren().add(imageView);
    }

    public static void playMusic(StackPane stackPane, String audioPath){
        MediaView view = new MediaView();
        Media soundtrack = new Media(new File(audioPath).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(soundtrack);
        view.setMediaPlayer(mediaPlayer);
        stackPane.getChildren().add(view);
        mediaPlayer.play();

    }

    public static Button addButton(StackPane stackPane, String text, Stage stage){
        Button button = new Button(text);
        button.setPrefSize(200,10);
        button.setText(text);
        button.setFont(Constants.getFont());
        button.setStyle("-fx-background-color: #E7B557;");
        addMouseListener(button, text, stage);
        return button;
    }

    public static void  addMouseListener(Node node, String text, Stage stage){
        node.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                switch (text) {
                    case "Play":
                        gameInterface(stage);
                        break;
                    case "Help":
                        helpInterface(stage);
                        break;
                    case "About":
                        aboutInterface(stage);
                        break;
                }
            }
        });
    }

    public static void gameInterface(Stage stage){
        stage.close();
        stage.setFullScreen(true);
        stage.setScene(new Scene(new StackPane()));
        stage.show();
    }

    public static void helpInterface(Stage stage){

    }

    public static void aboutInterface(Stage stage){
        stage.close();
        stage.setFullScreen(true);
        stage.setScene(new Scene(new StackPane()));
        stage.show();
    }
}
