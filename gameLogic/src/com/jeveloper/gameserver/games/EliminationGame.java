/*
 * EliminationGame.java
 *
 * Created on March 27, 2007, 11:16 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.jeveloper.gameserver.games;

import com.jeveloper.gameserver.beans.Draw;
import com.jeveloper.gameserver.beans.EliminationPlay;
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
 * Will eliminate Play if none of the letters is matched
 * Aproximate rules: if all players are eliminated , there should be a continuous cicle
 *
 * @author serge
 */
public class EliminationGame extends BaseGame{
    
    /** Creates a new instance of EliminationGame */
    public EliminationGame() {
        super.getGameType().setGtype(GAMETYPE_E.ELIMINATION);
    }
    
    private int getTotalPlays(){
        int total = 0;
        for(Player player_obj : getPlayerList().getPlayers()){
            for(IPlay playobj : player_obj.getPlayer_combinations().getList()){
                total++;
            }
        }
        return total;
    }
    
    public void startGame()  {
        boolean contplay = true;
        CombinationGenerator cg = new CombinationGenerator(this.getGameType().getCombination_length());
        CharList server_key;
        Draw draw = null; // draw for use
        CharList match_list =new ArrayCharList(this.getGameType().getCombination_length());
        CharList new_ignored_letters = new ArrayCharList(); //used to temporarly holding ignored letters (for each draw)
        
        int total_eliminated =0; //counter
        //we need to know how many plays are there all together
        int total_plays = getTotalPlays();
        int drawid = 0; // sequenceID for draw
        EliminationPlay play; //blank object
        int round = 0;//this variables will tell me round number
        
        while(contplay){
            
            server_key = cg.getSorted();
            round++;
            //just a precaution in case a complete empty game will be initiated
            if (total_plays == 0)
                contplay=false;
            
            for(Player player_obj : getPlayerList().getPlayers()){
                //for each player will initiate this
                
                //for each play we will create a new draw object
                
                for(IPlay playobj : player_obj.getPlayer_combinations().getList()){
                    
                    if (playobj instanceof EliminationPlay){
                        
                        play = (EliminationPlay)playobj;
                        
                        draw = new Draw();
                        draw.setId(drawid++); //sequence
                        draw.setServerPlay(server_key);
                        
                        
                        draw.setPlayerUsername(player_obj.getName());
                        
                        if (play.isEliminated() == false){
                            
                            new_ignored_letters.clear();
                            match_list = GameUtility.getFiltered(play.getCombination(),server_key,play.getLetters_ignored(),new_ignored_letters);
                            
                            play.setLetters_matched(match_list);
                            
                            
                            draw.setLetters_matched(String.valueOf(match_list.toArray()));
                            draw.setLetters_ignored(String.valueOf(new_ignored_letters)); //purely for displaying
                            play.addPoints(match_list.size());
                            
                            
                            draw.setPoints(match_list.size());
                            draw.setTotalPoints(play.getPoints());
                            
                            
                            
                            if(match_list.size() == 0){
                                total_eliminated++;
                                play.setEliminated(true);
                            }
                            
                            //this only SHOULD be checked for NON eliminated
                            if (play.getPoints() == this.getGameType().getCombination_length()){
                                contplay = false;
                            }
                            
                        }//  if this play is eliminated
                        
                        draw.setPlay(play);                        
                        // for each player we add that draw to a list
                        addDraw(draw);
                        
                        
                        if (total_eliminated == total_plays){
                            //all are eliminated
                            //POINTS will be set to 0
                            contplay = false;
                        }
                        
                        
                    }//end of check of instance type
                    else{
                        contplay = false;
                    }
                    
                } //end of foreach for play
                
                player_obj.setPoints(GameUtility.getBest(player_obj.getPlayer_combinations().getList())); //best PLAY must be picked
                
            }// end of foreach player
            
            
            
            // just 1 player left and not eliminated in first round
            if ((total_eliminated  == (total_plays-1)) && round == 1   ){
                //one left in first round
                contplay=false;
            }
    
        }//end of while
        
        //Complete stage to resort the PlayerList based on who received most points
        Comparator comp = Collections.reverseOrder();
        Collections.sort(getPlayerList().getPlayers(),comp);
    }
  
    
    
}
