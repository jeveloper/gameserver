/*
 * BingoGame.java
 *
 * Created on March 27, 2007, 11:16 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.jeveloper.gameserver.games;

import com.jeveloper.gameserver.beans.Draw;
import com.jeveloper.gameserver.beans.Player;
import com.jeveloper.gameserver.core.GameUtility;
import com.jeveloper.gameserver.core.BaseGame;
import com.jeveloper.gameserver.core.IPlay;
import com.jeveloper.gameserver.CombinationGenerator;
import com.jeveloper.gameserver.core.GAMETYPE_E;
import java.util.Collections;
import java.util.Comparator;
import org.apache.commons.collections.primitives.ArrayCharList;
import org.apache.commons.collections.primitives.CharList;

/**
 * This game will work with single character only
 * @author serge
 */
public class BingoGame extends BaseGame{
    
    /** Creates a new instance of BingoGame */
    public BingoGame() {
        super.getGameType().setGtype(GAMETYPE_E.BINGO);
        super.getGameType().setCombination_length(1);
    }
    public void  startGame() {
        boolean contplay = true;
        CombinationGenerator cg = new CombinationGenerator(this.getGameType().getCombination_length());
        Character server_key;
        CharList serv_play; //temporary usage
        Draw draw; // draw for use
        CharList match_list =new ArrayCharList(this.getGameType().getCombination_length());
        
        
        int drawid = 0; // sequenceID for draw
        
        while(contplay){
            
            server_key = cg.getSingleChar();
            
            
            for(Player player_obj : getPlayerList().getPlayers()){
                //for each player will initiate this
                
                //for each play we will create a new draw object
                
                for(IPlay play : player_obj.getPlayer_combinations().getList()){
                    
                    
                    draw = new Draw();
                    draw.setId(drawid++); //sequence
                   
                    serv_play = new ArrayCharList(1);
                    serv_play.add(server_key);
                    draw.setServerPlay(serv_play);
                    serv_play.clear();
                    
                  
                    draw.setPlayerUsername(player_obj.getName());
                    
                    if (play.getCombination().contains(server_key) ){
                        
                        if (play.getLetters_ignored().contains(server_key) == false){
                            play.getLetters_ignored().add(server_key);
                            play.addPoints(1); //counted towards it
                            
                            play.getLetters_matched().add(server_key);
                            draw.setLetters_matched(server_key.toString());
                            
                            
                            draw.setPoints(1);
                            
                            
                        }else{
                            //repeating
                            draw.setLetters_ignored(server_key.toString());
                        }
                        
                        
                    }else{
                        // server Character was not found in the play
                      
                        
                    }
                    //have to set total points 
                    draw.setTotalPoints(play.getPoints());
                    draw.setPlay(play);
                    
                    // for each player we add that draw to a list
                    addDraw(draw);
                    
                   
                    
                    // if total points for a particular draw will reach the same size as the draw's length                                        
                    if (play.getPoints() == play.getCombination().size()){
                        contplay = false;
                    
                    }
                    
                } //end of foreach for play
                player_obj.setPoints(GameUtility.getBest(player_obj.getPlayer_combinations().getList())); //best PLAY must be picked
               
            }// end of foreach player
          
            
            
        }//end of while
        
        //Complete stage to resort the PlayerList based on who received most points
        Comparator comp = Collections.reverseOrder();
        Collections.sort(getPlayerList().getPlayers(),comp);
    }

}
