/*
 * TestElimination.java
 *
 * Created on April 3, 2007, 9:50 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.jeveloper.gameserver;

import com.jeveloper.gameserver.beans.EliminationPlay;
import com.jeveloper.gameserver.beans.Player;
import com.jeveloper.gameserver.beans.PlayerList;
import com.jeveloper.gameserver.core.BaseGame;

import com.jeveloper.gameserver.core.IPlay;
import com.jeveloper.gameserver.games.EliminationGame;

/**
 *
 * @author serge
 */
public class TestElimination {
    
    /** Creates a new instance of TestElimination */
    public TestElimination() {
    }
    
    public static void main(String[] args) {
        
        
        //the only issue is that the random object should not be reseeded during a game
        // the generation is too quick and the random number doesnt change
        
        long startTime = System.currentTimeMillis();
        BaseGame game;
        
        game = new EliminationGame();
        
        
        game.getGameType().setCombination_length(2);
        CombinationGenerator cg = new CombinationGenerator(game.getGameType().getCombination_length());
        
        //player list will be initialized on standard game
        
        Player player = new Player(1234,"Serge Bornow",game.getGameType().getMax_plays());
        IPlay play1 = new EliminationPlay(game.getGameType().getCombination_length());
        play1.setCombinationFromString("AB");
        player.addPlay(play1);
        //add another
        IPlay play11 = new EliminationPlay(game.getGameType().getCombination_length());
        play11.setCombinationFromString("TF");
        player.addPlay(play11);
        
        
        
        Player player2 = new Player(144,"Antonin Dvorak",game.getGameType().getMax_plays());
        IPlay play2 = new EliminationPlay(game.getGameType().getCombination_length());
        play2.setCombinationFromString("DS");
        player2.addPlay(play2);
        
        
        PlayerList playerlist = new PlayerList(game.getGameType().getMax_players());
        playerlist.addPlayer(player);
        playerlist.addPlayer(player2);
        
        //IMPORTANT
        // SETTING THE playerlist to game
        game.setPlayerList(playerlist);
        
        
            game.startGame();
        
        
        long endTime = System.currentTimeMillis();
        print(" Total time taken to play: " + (endTime - startTime) + " milliseconds");
        
    }
    public static void print(String str){
        System.out.println(str);
    }
    
    
}
