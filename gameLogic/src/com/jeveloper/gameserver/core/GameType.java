/*
 * GameType.java
 *
 * Created on April 1, 2007, 3:02 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.jeveloper.gameserver.core;

/**
 *
 * @author serge
 */
public class GameType implements java.io.Serializable{
    private int combination_length = 7; //number of picks
    private int max_players = 10; // number of players
    private int max_plays = 0; // number of combinations
    private double cost_per_play = 0.0;
    private int max_winner; // only this number of players will be selected for winners
    private double ranked_winner_percentage; 
    
    private GAMETYPE_E gtype;
    
    /** Creates a new instance of GameType */
    public GameType() {
    }

    public int getCombination_length() {
        return combination_length;
    }

    public void setCombination_length(int combination_length) {
        this.combination_length = combination_length;
    }

    public int getMax_players() {
        return max_players;
    }

    public void setMax_players(int max_players) {
        this.max_players = max_players;
    }

    public int getMax_plays() {
        return max_plays;
    }

    public void setMax_plays(int max_plays) {
        this.max_plays = max_plays;
    }

    public double getCost_per_play() {
        return cost_per_play;
    }

    public void setCost_per_play(double cost_per_play) {
        this.cost_per_play = cost_per_play;
    }

    public int getMax_winner() {
        return max_winner;
    }

    public void setMax_winner(int max_winner) {
        this.max_winner = max_winner;
    }

    public double getRanked_winner_percentage() {
        return ranked_winner_percentage;
    }

    public void setRanked_winner_percentage(double ranked_winner_percentage) {
        this.ranked_winner_percentage = ranked_winner_percentage;
    }

    public GAMETYPE_E getGtype() {
        return gtype;
    }

    public void setGtype(GAMETYPE_E gtype) {
        this.gtype = gtype;
    }
    
}
