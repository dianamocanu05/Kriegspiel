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
        IntelligentPlayer intelligentPlayer = new IntelligentPlayer("Emperor Napoleon", "white");
        intelligentPlayer.setBoard(new Board("DOWN"));
        intelligentPlayer.setGame(game);

        IntelligentPlayer intelligentPlayer1 = new IntelligentPlayer("Marshall Kutuzov", "black");
        intelligentPlayer1.setBoard(new Board("UP"));
        intelligentPlayer1.setGame(game);



        game.addPlayer(intelligentPlayer);
        game.addPlayer(intelligentPlayer1);
    }


}
