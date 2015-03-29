/*
 * TestBingo.java
 *
 * Created on April 3, 2007, 9:50 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.jeveloper.gameserver;

import com.jeveloper.gameserver.beans.Play;
import com.jeveloper.gameserver.beans.Player;
import com.jeveloper.gameserver.beans.PlayerList;
import com.jeveloper.gameserver.core.BaseGame;

import com.jeveloper.gameserver.core.IPlay;
import com.jeveloper.gameserver.games.BingoGame;

/**
 *
 * @author serge
 */
public class TestBingo {
    
    /** Creates a new instance of TestBingo */
    public TestBingo() {
    }
    
    public static void main(String[] args) {
        
        
        //the only issue is that the random object should not be reseeded during a game
        // the generation is too quick and the random number doesnt change
        
        long startTime = System.currentTimeMillis();
        BaseGame game;
        
        game = new BingoGame();
        
        
        
        
        CombinationGenerator cg = new CombinationGenerator(game.getGameType().getCombination_length());
        
        //player list will be initialized on standard game
        
        Player player = new Player(1234,"Serge Bornow",game.getGameType().getMax_plays());
        IPlay play1 = new Play();
        play1.setCombinationFromString("ABC");
        player.addPlay(play1);
        //add another
        IPlay play11 = new Play();
        play11.setCombinationFromString("TZU");
        player.addPlay(play11);
        
        
        
        Player player2 = new Player(144,"Antonin Dvorak",game.getGameType().getMax_plays());
        IPlay play2 = new Play();
        play2.setCombinationFromString("DOA");
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
