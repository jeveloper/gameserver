/*
 * Draw.java
 *
 * Created on March 15, 2007, 10:20 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.jeveloper.gameserver.beans;

import com.jeveloper.gameserver.core.IPlay;
import java.io.Serializable;
import org.apache.commons.collections.primitives.CharList;

/**
 * The live draw
 * @author serge
 */
public class Draw implements Serializable{
    
    
    
    private int id =0; //interal iterative sequence
    
    private String playerUsername;
    
    
    // letters ignored are letters matched but in the previous draws
    private String letters_ignored = ""; // NOTE: this field is important only for returning Object
    private String letters_matched = ""; //only for displaying for a draw
    
    private int points = 0; // is set for each draw
    private int totalPoints = 0; //gets accumulated for a particular draw, also stored in play.getPoints
    
    private CharList serverPlay = null;
    private IPlay play;
    
    /** Creates a new instance of Draw */
    public Draw() {
    }
    
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    
    
    public IPlay getPlay() {
        return play;
    }
    
    public void setPlay(IPlay play) {
        this.play = play;
    }
    
    @Override
    public String toString(){
        String elim = "";
        if (this.play instanceof EliminationPlay){
            EliminationPlay pd = (EliminationPlay)play;
            if (pd.isEliminated()){
                elim = "Eliminated";
            }
        }
        return " ID: "+ id +
                "; serverplay: " +
                String.valueOf(serverPlay.toArray()) +
                "; playerid: " +                 
                playerUsername + "; Letters ignored "+
                this.letters_ignored + "; Letters matched: "+
                this.letters_matched +
                "; Points: "+ this.getPoints() +
                "; Total Points:" + this.getTotalPoints() +
                "  " + elim;
        
    }
    
    public String getLetters_ignored() {
        return letters_ignored;
    }
    
    public void setLetters_ignored(String letters_ignored) {
        this.letters_ignored = letters_ignored;
    }
    
    public String getLetters_matched() {
        return letters_matched;
    }
    
    public void setLetters_matched(String letters_matched) {
        this.letters_matched = letters_matched;
    }
    
    public int getPoints() {
        return points;
    }
    
    public void setPoints(int points) {
        this.points = points;
    }
    
    public int getTotalPoints() {
        return totalPoints;
    }
    
    public void setTotalPoints(int totalPoints) {
        this.totalPoints = totalPoints;
    }
    
    public CharList getServerPlay() {
        return serverPlay;
    }
    
    public void setServerPlay(CharList serverPlay) {
        this.serverPlay = serverPlay;
    }

    public String getPlayerUsername() {
        return playerUsername;
    }

    public void setPlayerUsername(String playerUsername) {
        this.playerUsername = playerUsername;
    }
    
    
    
    
    
}
