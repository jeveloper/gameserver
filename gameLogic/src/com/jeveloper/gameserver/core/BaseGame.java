/*
 * BaseGame.java
 *
 * Created on March 25, 2007, 10:16 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.jeveloper.gameserver.core;

import com.jeveloper.gameserver.beans.Draw;
import com.jeveloper.gameserver.beans.DrawList;
import com.jeveloper.gameserver.beans.PlayerList;



/**
 *
 * @author serge
 */
public abstract class BaseGame implements IBaseGame,java.io.Serializable {
    
    
    private long id;
    
     
    //TODO decide where to initilize this , so far IT IS NOT needed since it will be copied TO
    private PlayerList playerList; //single player list of Player objects
    private DrawList drawList; //holds all the final draws
    
    private GameType gameType; 
    
    /**
     * Creates a new instance of BaseGame
     */
    public BaseGame() {  
        gameType = new GameType();
        drawList = new DrawList();        
    }
    
    public void addDraw(Draw draw){
        this.drawList.addDraw(draw);
    }
    
    /**
     * 
     * @return Instance ID
     */
     public long getId() {
        return id;
    }

    /**
     * Set Instance Id
     * @param id 
     */
    public void setId(long id) {
        this.id = id;
    }

    public PlayerList getPlayerList() {
        return playerList;
    }

    public void setPlayerList(PlayerList playerList) {
        this.playerList = playerList;
    }

    public DrawList getDrawList() {
        return drawList;
    }

    public void setDrawList(DrawList drawList) {
        this.drawList = drawList;
    }

    public GameType getGameType() {
        return gameType;
    }

    public void setGameType(GameType gameType) {
        this.gameType = gameType;
    }

    
}
