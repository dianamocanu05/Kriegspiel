package org.example.GUI;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.example.Constants;
import org.example.GameLogic.Board;
import org.example.GameLogic.PieceType;
import org.example.GameLogic.Position;

public class GameInterface {
    private Stage stage;
    private static List<Rectangle> chessBoard = new ArrayList<>();
    public GameInterface(Stage stage){
        this.stage = stage;
    }
    public void initialize(){
        initScreen();

    }

    public void initScreen(){
        StackPane stackPane = new StackPane();
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
        addButtonMouseListener(button, text, stage);
        return button;
    }

    public static Button addButton(StackPane stackPane, String text, Stage stage, int height, int width, Font font){
        Button button = new Button(text);
        button.setPrefSize(height,width);
        button.setText(text);
        button.setFont(font);
        button.setStyle("-fx-background-color: #E7B557;");
        addButtonMouseListener(button, text, stage);
        return button;
    }

    public static void  addButtonMouseListener(Node node, String text, Stage stage){
        node.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                stage.close();
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
                    case "Back":
                        new GameInterface(stage).initialize();
                        break;
                }
            }
        });
    }

    public static void gameInterface(Stage stage){
        StackPane stackPane = new StackPane();
        addImage(stackPane,Constants.getBorodinoMap());
        playMusic(stackPane, Constants.getAudio2());
        createChessBoard(stackPane, new Board());
        stage.setScene(new Scene(stackPane));
        stage.show();
    }

    public static void helpInterface(Stage stage){

    }

    public static void aboutInterface(Stage stage){
        StackPane stackPane = new StackPane();
        addImage(stackPane,Constants.getAboutImg());
        playMusic(stackPane, Constants.getAudio1());
        Button back = addButton(stackPane,"Back",stage,100,5,Constants.getLittleFont());
        HBox hBox = new HBox();
        hBox.setAlignment(Pos.BOTTOM_RIGHT);
        hBox.getChildren().add(back);
        stackPane.getChildren().add(hBox);
        stage.setScene(new Scene(stackPane));
        stage.show();
    }

    public static void createChessBoard(StackPane stackPane, Board board){
        GridPane pane = new GridPane();

        int count = 0;
        int size = 70;

        GridPane lettersNumbers = new GridPane();
        lettersNumbers.setAlignment(Pos.CENTER);


        for(int i=0;  i<8;i++){
            Label letter = new Label(String.valueOf((char)('A' + i)));
            letter.setPrefSize(70,70);
            letter.setAlignment(Pos.BOTTOM_CENTER);
            letter.setFont(Constants.getFont());
            letter.setTextFill(Color.RED);

            Label number = new Label(String.valueOf(8-i));
            number.setPrefSize(70,70);
            number.setAlignment(Pos.BOTTOM_LEFT);
            number.setFont(Constants.getFont());
            number.setTextFill(Color.RED);

            lettersNumbers.add(letter,i,10);
            lettersNumbers.add(number,0,i);
        }

        for (int i = 1; i <= 8; i++) {
            count++;
            for (int j = 1; j <= 8; j++) {

                    Rectangle rectangle = new Rectangle(size, size, size, size);
                    if (count % 2 == 0) {
                        rectangle.setFill(Color.WHITE);
                    }
                    rectangle.setFocusTraversable(false);
                    rectangle.setOpacity(0.5);

                    chessBoard.add(rectangle);
                    pane.add(rectangle, j, i);
                    count++;

                Position position = new Position((char)('A' + j -1), 9-i);
                PieceType piece = board.getPieceAtPosition(position);

                if(piece != null){
                    ImageView pieceImage = new ImageView(Constants.getPiecePath(piece,"white"));
                    addPieceMouseListener(pieceImage, piece, pane, board);
                    pane.add(pieceImage,j,i);
                }
                }
            }

        pane.setGridLinesVisible(true);
        pane.setAlignment(Pos.CENTER);
        stackPane.getChildren().add(lettersNumbers);
        stackPane.getChildren().add(pane);
    }

    private static void addPieceMouseListener(ImageView imageView, PieceType pieceType, GridPane pane, Board board){
        imageView.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                mouseEvent.consume();
            }
        });

        imageView.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent dragEvent) {

            }
        });

        imageView.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Rectangle target = getTargetRectangle(mouseEvent.getSceneX(), mouseEvent.getSceneY());
                movePiece(pieceType, imageView, pane, target, board);
            }
        });
    }

    private static void movePiece(PieceType pieceType, ImageView piece, GridPane pane, Rectangle target, Board board){
        int i = GridPane.getRowIndex(target);
        int j = GridPane.getColumnIndex(target);
        pane.getChildren().remove(piece);
        pane.add(piece, j,i);

        board.replace(pieceType, new Position((char)('A' + j - 1),8- i));
        /*System.out.print("Moved " + pieceType.toString() + " to ");
        new Position((char) ('A' + j - 1), 8- i).print();
        */
    }

    private static Rectangle getTargetRectangle(double x, double y){
        for(Rectangle rectangle : chessBoard){
            if(rectangle.getBoundsInParent().contains(new Point2D(x,y))){
                return  rectangle;
            }
        }
        return null;
    }
}
