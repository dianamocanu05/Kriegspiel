package org.example.GameLogic;

import org.example.GameLogic.Players.HumanPlayer;
import org.example.GameLogic.Players.IntelligentPlayer;

public class InitPlayers {

    public static void initPvA(Game game){
        HumanPlayer humanPlayer = new HumanPlayer("Emperor Napoleon", "white");
        humanPlayer.setBoard(new Board("DOWN"));
        humanPlayer.setGame(game);

        IntelligentPlayer intelligentPlayer = new IntelligentPlayer("Marshall Kutuzov", "black");
        intelligentPlayer.setBoard(new Board("UP"));
        intelligentPlayer.setGame(game);

        game.addPlayer(humanPlayer);
        game.addPlayer(intelligentPlayer);
    }

    public static void initAvA(Game game){
        Board board1 = new Board("DOWN");
        IntelligentPlayer intelligentPlayer = new IntelligentPlayer("Emperor Napoleon", "white");
        intelligentPlayer.setBoard(board1);
        intelligentPlayer.setGame(game);

        Board board2 = new Board("UP");
        IntelligentPlayer intelligentPlayer1 = new IntelligentPlayer("Marshall Kutuzov", "black");
        intelligentPlayer1.setBoard(board2);
        intelligentPlayer1.setGame(game);



        game.addPlayer(intelligentPlayer1);
        game.addPlayer(intelligentPlayer);
    }


}
