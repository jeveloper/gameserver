/*
 * Player.java
 *
 * Created on March 15, 2007, 10:19 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.jeveloper.gameserver.beans;

import com.jeveloper.gameserver.core.IPlay;
import java.io.Serializable;

/**
 *
 * @author serge
 */
public class Player implements Serializable, Comparable<Player>{
    
    private int id =0; //not internal but external
    private String name ="";
    
    private Integer  points=0; // total points , WE MIGHT NEED To keep this in a draw or player list, or have Ranking within game
    
    private PlayList player_combinations;
    
    /** Creates a new instance of Player */
    public Player() {
    }
    public Player(int _id, String  _name) {
        setId(_id);
        setName(_name);
        setPlayer_combinations(new PlayList());
    }
    
    public Player(int _id, String  _name, int max_plays) {
        setId(_id);
        setName(_name);
        setPlayer_combinations(new PlayList(max_plays));
    }
    
    public void addPlay(IPlay play){
        player_combinations.addPlay(play);
    }
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public Integer getPoints() {
        return points;
    }
    
    public void setPoints(Integer points) {
        this.points = points;
    }
    
    public PlayList getPlayer_combinations() {
        return player_combinations;
    }
    
    public void setPlayer_combinations(PlayList player_combinations) {
        this.player_combinations = player_combinations;
    }

    public int compareTo(Player o) {                
       return points.compareTo(o.getPoints());               
    }
    
    
}
