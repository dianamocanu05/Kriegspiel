package org.example.GameLogic.Players;

import javafx.scene.image.ImageView;
import org.example.GameLogic.*;
import org.example.GameLogic.Algorithms.MCTS;
import org.example.GameLogic.Algorithms.Node;
import org.example.GameLogic.Algorithms.State;

import java.util.ArrayList;
import java.util.List;

/**
 * AI powered Player
 */
public class IntelligentPlayer extends Player{
    private List<Image> images= new ArrayList<>();


    public IntelligentPlayer(String name, String color){
        super(name, color);
    }

    public void addImage(Position piecePosition, ImageView imageView){
        this.images.add(new Image(piecePosition,imageView));
    }

    public void replaceImage(Position piecePosition, ImageView imageView){
        for(Image image : this.images){
            int index = this.images.indexOf(image);
            if(image.getPosition().equals(piecePosition)){
                image.setImageView(imageView);
                this.images.set(index, image);
            }
        }
    }

    public void eraseImage(Position piecePosition){
        replaceImage(piecePosition, new ImageView());
    }

    public ImageView getImageAtPosition(Position position){
        for(Image image : images){
            if(image.getPosition().equals(position)){
                return image.getImageView();
            }
        }
        return null;
    }

    public List<Image> getImages(){
        return images;
    }
    @Override
    public Move attemptMove() throws InterruptedException {
        try{
            Thread.sleep(4*1000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        State state = new State(this.board,null);

        state.setOpponentBoard(game.getOtherPlayer(this).getBoard());
        MCTS mcts = new MCTS(new Node(state, null));
        State result = mcts.run();

        lastMove = result.getCreatorMove();
        lastMove.print();
        return lastMove;
    }

}
