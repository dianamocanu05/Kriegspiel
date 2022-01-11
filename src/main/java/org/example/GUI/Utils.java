package org.example.GUI;

import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.ImageCursor;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.BlendMode;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.Duration;
import org.example.Constants;
import org.example.GameLogic.Logger;
import org.example.GameLogic.Position;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Various GUI util function
 */
public class Utils {

    private static StringBuilder notes = new StringBuilder();

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

        //addImageTooltip(imageView, "The Referee");

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                stackPane.getChildren().add(imageView);
            }
        });
    }

    public static void addPen(StackPane stackPane) {
        Image image = new Image(Constants.getPenImg());
        ImageView imageView = new ImageView(image);
        //340 x 750
        imageView.setFitWidth(75);
        imageView.setFitHeight(90);
        imageView.setTranslateX((float) Constants.getWidth() / 2 - 75);
        imageView.setTranslateY((float) -1 * Constants.getHeight() / 2 + 50);
        //addImageTooltip(imageView, "Add note");

        imageView.setOnMouseClicked(mouseEvent -> {
            Stage notebook = new Stage();
            createStage(notebook);
            notebook.show();

        });


        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                stackPane.getChildren().add(imageView);
            }
        });


    }

    private static void createStage(Stage notebook) {
        notebook.setX(1200);
        notebook.setY(300);
        notebook.setTitle("Notebook");
        notebook.setWidth(300);
        notebook.setHeight(500);

        StackPane sp = new StackPane();

        ImageView view = new ImageView(new Image(Constants.getOldPaperImg()));
        view.setFitHeight(500);
        view.setFitWidth(300);
        sp.getChildren().add(view);


        GridPane gridPane = new GridPane();
//        Button customButton = new Button("Add note about position");
//        customButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
//            @Override
//            public void handle(MouseEvent mouseEvent) {
//
//                GridPane grid = new GridPane();
//                grid.setAlignment(Pos.CENTER);
//                grid.setHgap(10);
//                grid.setVgap(10);
//                grid.setPadding(new Insets(25, 25, 25, 25));
//
//                Text textField = new Text("Add note about position");
//                textField.setFont(Constants.getLittleFont());
//                grid.add(textField,0,0,2,1);
//                Label userName = new Label("Position");
//                grid.add(userName, 0, 1);
//
//                TextField userTextField = new TextField();
//                grid.add(userTextField, 1, 1);
//
//                Label pw = new Label("Note");
//                grid.add(pw, 0, 2);
//
//                TextField pwBox = new TextField();
//                grid.add(pwBox, 1, 2);
//
//                //sp.getChildren().add(grid);
//                Scene scene = new Scene(grid, 300, 275);
//                Stage stage = new Stage();
//                scene.setFill(Color.rgb(250, 207, 127 ));
//                stage.setScene(scene);
//                stage.setX(1200);
//                stage.setY(300);
//                stage.show();
//
//            }
//        });
        final Text[] noteText = {new Text(notes.toString())};
        Button button = new Button("Add note");
        button.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        TextArea textArea = new TextArea();
                        textArea.setMaxHeight(200);
                        textArea.setMaxWidth(300);
                        sp.getChildren().add(textArea);
                        textArea.setOnKeyPressed(new EventHandler<KeyEvent>() {
                            @Override
                            public void handle(KeyEvent keyEvent) {
                                if (keyEvent.getCode() == KeyCode.ENTER) {
                                    String text = textArea.getText();
                                    notes.append(text).append("\n");
                                    gridPane.getChildren().remove(noteText[0]);
                                    noteText[0] = new Text(notes.toString());
                                    gridPane.addRow(3, noteText[0]);
                                    sp.getChildren().remove(textArea);
                                }
                            }
                        });
                    }
                });
            }
        });

        gridPane.setAlignment(Pos.TOP_CENTER);
        Text title = new Text("Notebook");
        title.setFont(Constants.getFont());
        gridPane.addRow(1, title);
        gridPane.addRow(2, button);
        gridPane.addRow(3, noteText[0]);
        sp.getChildren().add(gridPane);


        Scene scene = new Scene(sp);
        notebook.setScene(scene);

    }


    public static ScrollPane addHistory(StackPane stackPane) {
        Image image = new Image(Constants.getBooksImg());
        ImageView imageView = new ImageView(image);
        //299 x 410
        imageView.setFitWidth(75);
        imageView.setFitHeight(90);
        imageView.setTranslateX((float) -1 * Constants.getWidth() / 2 + 100);
        imageView.setTranslateY((float) -1 * Constants.getHeight() / 2 + 50);
        //addImageTooltip(imageView, "See message history");
        Stage history = new Stage();
        ScrollPane scrollPane = new ScrollPane();
        imageView.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                history.setX(300);
                history.setY(300);
                history.setTitle("War chronicle");
                history.setWidth(600);
                history.setHeight(800);
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


    public static void updateHistory(ScrollPane scrollPane, Logger logger) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                Text text = new Text(logger.getLogs());
                text.setFont(Constants.getLittleFont());
                scrollPane.setContent(text);
            }
        });
    }

    public static void addImageTooltip(ImageView imageView, String legend) {
        Tooltip tp = new Tooltip(legend);
        tp.setStyle(Constants.getLittleStringFont());
        final double[] x = new double[1];
        final double[] y = new double[1];

        imageView.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                x[0] = mouseEvent.getScreenX();
                y[0] = mouseEvent.getScreenY();
            }
        });

        imageView.setOnMouseMoved(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Tooltip.install(imageView, tp);
                tp.setShowDuration(new Duration(100));
                tp.show(imageView, x[0], y[0]);

            }
        });

        imageView.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                tp.hide();
                Tooltip.uninstall(imageView, tp);
            }
        });
    }

    public static void addTooltip(Rectangle rectangle) {
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
                Tooltip.install(rectangle, tp);
                tp.setShowDuration(new Duration(100));
                tp.show(rectangle, x[0], y[0]);

            }
        });

        rectangle.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                tp.hide();
                Tooltip.uninstall(rectangle, tp);
            }
        });
    }


}
