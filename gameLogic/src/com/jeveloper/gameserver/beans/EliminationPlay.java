/*
 * EliminationPlay.java
 *
 * Created on April 1, 2007, 6:20 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.jeveloper.gameserver.beans;

/**
 * enhancement Should we create IPlay 
 * @author serge
 */
public class EliminationPlay extends Play {
    private boolean eliminated = false;
    
    
    /** Creates a new instance of EliminationPlay */
    public EliminationPlay() {
    }
    public EliminationPlay(int length) {
        super(length);
    }
    public boolean isEliminated() {
        return eliminated;
    }
    
    public void setEliminated(boolean eliminated) {
        this.eliminated = eliminated;
    }
}
