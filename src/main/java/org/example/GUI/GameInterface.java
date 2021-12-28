package org.example.GUI;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.effect.BlendMode;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import org.example.Constants;
import org.example.GameLogic.*;
import org.example.GameLogic.Players.HumanPlayer;
import org.example.GameLogic.Players.Player;

public class GameInterface {
    private Stage stage;
    private static List<Rectangle> chessBoard = new ArrayList<>();
    private static Rectangle initial;
    private Player currentPlayer;
    private StackPane stackPane;
    private StackPane gamePane = new StackPane();
    private Game game;
    public boolean chosen = false;
    public final Object mutex = new Object();
    private Text playerName;
    public Move lastMove;
    private ScrollPane history;
    private long start;

    public GameInterface(Stage stage) {
        this.stage = stage;
    }

    public void initialize() {
        Platform.setImplicitExit(false);
        initScreen();
    }

    public StackPane getGamePane() {
        return gamePane;
    }

    public Text getPlayerName() {
        return playerName;
    }

    public ScrollPane getHistory() {
        return history;
    }

    public void initScreen() {
        stackPane = new StackPane();
        //Utils.playMusic(stackPane, Constants.getAudio1());
        Utils.addImage(stackPane, Constants.getBackgroundImg());
        VBox vbox = new VBox();
        vbox.setSpacing(10);
        vbox.setAlignment(Pos.BOTTOM_CENTER);

        Button play = addButton(stackPane, "Play", stage);
        Button help = addButton(stackPane, "Help", stage);
        Button about = addButton(stackPane, "About", stage);

        vbox.getChildren().addAll(play, help, about);

        stackPane.getChildren().add(vbox);
        stage.setTitle("Kriegspiel");
        Scene scene = new Scene(stackPane);
        stage.setScene(scene);
        stage.show();

    }


    public Button addButton(StackPane stackPane, String text, Stage stage) {
        Button button = new Button(text);
        button.setPrefSize(200, 10);
        button.setText(text);
        button.setFont(Constants.getFont());
        button.setStyle("-fx-background-color: #E7B557;");
        addButtonMouseListener(button, text, stage);
        return button;
    }

    public Button addButton(StackPane stackPane, String text, Stage stage, int height, int width, Font font) {
        Button button = new Button(text);
        button.setPrefSize(height, width);
        button.setText(text);
        button.setFont(font);
        button.setStyle("-fx-background-color: #E7B557;");
        addButtonMouseListener(button, text, stage);
        return button;
    }

    public void addButtonMouseListener(Node node, String text, Stage stage) {
        node.setOnMouseClicked(mouseEvent -> {
            switch (text) {
                case "Play":
                    try {
                        gameInterface(stage);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
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
        });
    }

    public void gameInterface(Stage stage) throws InterruptedException {
        this.currentPlayer = game.getCurrentPlayer();

        stage.close();


        Utils.addImage(gamePane, Constants.getBorodinoMap());
        //Utils.playMusic(gamePane, Constants.getAudio2());
        Utils.addButler(gamePane);
        Utils.addPen(gamePane);
        history = Utils.addHistory(gamePane);
        createChessBoard(gamePane);
        playerName = Utils.addNameText(currentPlayer.getName(), gamePane);
        stage.setScene(new Scene(gamePane));
        start = System.currentTimeMillis();
        stage.show();

    }

    public long getStart() {
        return start;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public static void helpInterface(Stage stage) {

    }

    public void aboutInterface(Stage stage) {
        stage.close();
        StackPane stackPane = new StackPane();
        Utils.addImage(stackPane, Constants.getAboutImg());
        Utils.playMusic(stackPane, Constants.getAudio1());
        Button back = addButton(stackPane, "Back", stage, 100, 5, Constants.getLittleFont());
        HBox hBox = new HBox();
        hBox.setAlignment(Pos.BOTTOM_RIGHT);
        hBox.getChildren().add(back);
        stackPane.getChildren().add(hBox);
        stage.setScene(new Scene(stackPane));
        stage.show();
    }


    public void createChessBoard(StackPane stackPane) {
        GridPane pane = new GridPane();

        int count = 0;
        int size = 70;

        GridPane lettersNumbers = new GridPane();
        lettersNumbers.setAlignment(Pos.CENTER);


        for (int i = 0; i < 8; i++) {
            Label letter = new Label(String.valueOf((char) ('A' + i)));
            letter.setPrefSize(70, 70);
            letter.setAlignment(Pos.BOTTOM_CENTER);
            letter.setFont(Constants.getFont());
            letter.setTextFill(Color.RED);

            Label number = new Label(String.valueOf(8 - i));
            number.setPrefSize(70, 70);
            number.setAlignment(Pos.BOTTOM_LEFT);
            number.setFont(Constants.getFont());
            number.setTextFill(Color.RED);

            lettersNumbers.add(letter, i, 10);
            lettersNumbers.add(number, 0, i);
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


                //Point2D coord = rectangle.localToParent(rectangle.getX(),rectangle.getY());

                Position position = new Position((char) ('A' + j - 1), 9 - i);
                PieceType piece = currentPlayer.getBoard().getPieceAtPosition(position);

                if (piece != PieceType.NONE) {
                    ImageView pieceImage = new ImageView(Constants.getPiecePath(piece, currentPlayer.getColor()));
                    addPieceMouseListener(pieceImage, piece, pane);
                    pane.add(pieceImage, j, i);
                }
            }
        }

        pane.setGridLinesVisible(true);
        pane.setAlignment(Pos.CENTER);
        stackPane.getChildren().add(lettersNumbers);
        stackPane.getChildren().add(pane);
    }


    private void addPieceMouseListener(ImageView imageView, PieceType pieceType, GridPane pane) {
        imageView.setOnMousePressed(mouseEvent -> {
            initial = getTargetRectangle(mouseEvent.getSceneX(), mouseEvent.getSceneY());
            mouseEvent.consume();
        });

        imageView.setOnMouseDragged(Event::consume);

        imageView.setOnMouseReleased(mouseEvent -> {

            Rectangle target = getTargetRectangle(mouseEvent.getSceneX(), mouseEvent.getSceneY());
            lastMove = getMove(pieceType, target);
            synchronized (mutex) {
                chosen = true;
                mutex.notify();
            }
            if(isPositionEmpty(computePosition(target))) {
                movePiece(pieceType, imageView, pane, target);
                currentPlayer.getBoard().replace(lastMove);
            }

        });
    }

    private boolean isPositionEmpty(Position position) {
        return (currentPlayer.getBoard().getPieceAtPosition(position) == PieceType.NONE);
    }

    private Position computePosition(Rectangle rectangle) {
        int i = GridPane.getRowIndex(rectangle);
        int j = GridPane.getColumnIndex(rectangle);

        return new Position((char) ('A' + j -1), 9 - i);
    }

    private Move getMove(PieceType pieceType, Rectangle target) {
        int i = GridPane.getRowIndex(target);
        int j = GridPane.getColumnIndex(target);

        Position newPosition = new Position((char) ('A' + j - 1), 9 - i);
        Position oldPosition = new Position((char) ('A' + GridPane.getColumnIndex(initial) - 1), 9 - GridPane.getRowIndex(initial));

        return new Move(oldPosition, newPosition, pieceType, currentPlayer.getBoard(), currentPlayer);
    }

    public void movePiece(PieceType pieceType, ImageView piece, GridPane pane, Rectangle target) {
        int i = GridPane.getRowIndex(target);
        int j = GridPane.getColumnIndex(target);

        pane.getChildren().remove(piece);
        pane.add(piece, j, i);
    }

    private static Rectangle getTargetRectangle(double x, double y) {
        for (Rectangle rectangle : chessBoard) {
            if (rectangle.getBoundsInParent().contains(new Point2D(x, y))) {
                return rectangle;
            }
        }
        return null;
    }


}
