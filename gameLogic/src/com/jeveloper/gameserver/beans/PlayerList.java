/*
 * PlayerList.java
 *
 * Created on March 21, 2007, 9:26 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.jeveloper.gameserver.beans;

import com.jeveloper.gameserver.core.NoItemException;
import java.util.ArrayList;
import java.util.List;



/**
 *
 * @author serge
 */
public class PlayerList implements java.io.Serializable {
    
    
    private List<Player> players; 
    
    /** Creates a new instance of PlayerList */
    public PlayerList() {
        players = new ArrayList();
    }
    
    public PlayerList(int max_players) {
        players = new ArrayList(max_players);
    }
    
    public void addPlayer( Player player){
        players.add(player);
    }
    public Player getPlayer(int index) throws NoItemException{
        try {
            Player player = this.getPlayers().get(index);
            if (player != null){
                return player;
            }else{
                throw new NoItemException("No player with this index "+ index);
            }
        }
         catch(java.lang.IndexOutOfBoundsException ex){
             throw new NoItemException("No player with this id "+ index);
         }
         
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }
    
    
    
    
}
