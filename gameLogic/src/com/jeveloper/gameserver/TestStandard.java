/*
 * TestStandard.java
 *
 * Created on April 3, 2007, 9:50 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.jeveloper.gameserver;

import com.jeveloper.gameserver.core.BaseGame;
import experimental.SomeLetter;
import java.util.ArrayList;
import java.util.List;
import org.josql.Query;
import org.josql.QueryExecutionException;
import org.josql.QueryParseException;
import org.josql.QueryResults;




/**
 *
 * @author serge
 */
public class TestStandard {
    
    /** Creates a new instance of TestStandard */
    public TestStandard() {
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        
        //the only issue is that the random object should not be reseeded during a game
        // the generation is too quick and the random number doesnt change
        
        long startTime = System.currentTimeMillis();
        BaseGame game;
       
        game = new StandardGame();
         
         
        game.getGameType().setCombination_length(3);
        CombinationGenerator cg = new CombinationGenerator(game.getGameType().getCombination_length());
         
        //player list will be initialized on standard game
         
        Player player = new Player(1234,"Serge Bornow",game.getGameType().getMax_plays());
        IPlay play1 = new Play(game.getGameType().getCombination_length());
        play1.setCombinationFromString("ABC");
        player.addPlay(play1);
        //add another
        IPlay play11 = new Play(game.getGameType().getCombination_length());
        play11.setCombinationFromString("TFZ");
        player.addPlay(play11);
         
         
         
        Player player2 = new Player(144,"Antonin Dvorak",game.getGameType().getMax_plays());
        IPlay play2 = new Play(game.getGameType().getCombination_length());
        play2.setCombinationFromString("DSK");
        player2.addPlay(play2);
         
        Player player3 = new Player(23,"Mahler",game.getGameType().getMax_plays());
        IPlay play3 = new Play(game.getGameType().getCombination_length());
        play3.setCombinationFromString("ZYU");
        player3.addPlay(play3);
         
         
         
         
        PlayerList playerlist = new PlayerList(game.getGameType().getMax_players());
        playerlist.addPlayer(player);
        playerlist.addPlayer(player2);
        playerlist.addPlayer(player3);
         
        //IMPORTANT
        // SETTING THE playerlist to game
        game.setPlayerList(playerlist);
         
        try{
            game.startGame();
         
         
        }catch(Exception ex){
         
        }
  
        
        List lst = new ArrayList();
        lst.add(new SomeLetter('a'));
        lst.add(new SomeLetter('b'));
        lst.add(new SomeLetter('c'));
        lst.add(new SomeLetter('d'));
        
        
        List myObjs = lst;
        
// Create a new Query.
        Query q = new Query();
        try {
            
// Parse the SQL you are going to use.
            q.parse("SELECT * FROM experimental.SomeLetter where sl NOT in ('a','c') ");
        } catch (QueryParseException ex) {
            ex.printStackTrace();
        }
        QueryResults qr;
        try {
            qr = q.execute(myObjs);
            List res = qr.getResults();
            
            for (int i = 0; i < res.size(); i++) {
                
               SomeLetter ch = (SomeLetter)res.get(i);
                System.out.println(" got :"+ch.getSl());
                
                
            }
        } catch (QueryExecutionException ex) {
            ex.printStackTrace();
        }
        
// Cycle over the query results.
        
        double out = 10.4*14/113;
        //1.288
        //should be 1.28
        //java.lang.Math.round(ch).toString()
        
        
      
        long endTime = System.currentTimeMillis();
        print(" Total time taken to play: " + (endTime - startTime) + " milliseconds");
        
    }
    public static void print(String str){
        System.out.println(str);
    }
    
}
