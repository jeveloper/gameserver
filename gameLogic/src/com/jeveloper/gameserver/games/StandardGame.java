/*
 * StandardGame.java
 *
 * Created on March 25, 2007, 10:30 PM
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
 *
 * @author serge
 */
public class StandardGame extends BaseGame{
    
    
    
    /** Creates a new instance of StandardGame */
    public StandardGame() {
        super.getGameType().setGtype(GAMETYPE_E.STANDARD);
    }
    
    public void  startGame() {
        boolean contplay = true;
        CombinationGenerator cg = new CombinationGenerator(this.getGameType().getCombination_length());
        CharList server_key;
        Draw draw; // draw for use
        CharList match_list =new ArrayCharList(this.getGameType().getCombination_length());
        CharList new_ignored_letters = new ArrayCharList(); //used to temporarly holding ignored letters (for each draw)
        
        int drawid = 0; // sequenceID for draw
        
        while(contplay){
            //generates a Server Play
            server_key = cg.getSorted();
            
            for(Player player_obj : getPlayerList().getPlayers()){
                //for each player will initiate this
                
                //for each play we will create a new draw object
                
                for(IPlay play : player_obj.getPlayer_combinations().getList()){
                    
                    
                    draw = new Draw();
                    draw.setId(drawid++); //sequence
                    draw.setPlayerUsername(player_obj.getName());
                    draw.setServerPlay(server_key);
                    
                    
                    new_ignored_letters.clear();
                    match_list = GameUtility.getFiltered(play.getCombination(),server_key,play.getLetters_ignored(),new_ignored_letters);
                    
                    play.setLetters_matched(match_list);
                    
                    
                    draw.setLetters_matched(String.valueOf(match_list.toArray()));
                    draw.setLetters_ignored(String.valueOf(new_ignored_letters)); //purely for displaying
                    play.addPoints(match_list.size());
                    
                    draw.setPoints(match_list.size());
                    draw.setTotalPoints(play.getPoints());
                    
                    
                    draw.setPlay(play);
                    
                    // for each player we add that draw to a list
                    addDraw(draw);
                    
                    if (play.getPoints() == this.getGameType().getCombination_length()){
                        contplay = false;
                        //have to assign points
                        player_obj.setPoints(GameUtility.getBest(player_obj.getPlayer_combinations().getList())); //best PLAY must be picked
                    }
                    
                } //end of foreach for play
                
                // at this point we can add points to Player object
                
                player_obj.setPoints(GameUtility.getBest(player_obj.getPlayer_combinations().getList())); //best PLAY must be picked
                
                
            }// end of foreach player                        
            
        }//end of while
        
        
        //Complete stage to resort the PlayerList based on who received most points
        Comparator comp = Collections.reverseOrder();
        Collections.sort(getPlayerList().getPlayers(),comp);
        
    }    
}
