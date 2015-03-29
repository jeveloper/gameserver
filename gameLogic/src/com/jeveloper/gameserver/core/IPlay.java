/*
 * IPlay.java
 *
 * Created on April 1, 2007, 6:22 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.jeveloper.gameserver.core;

import org.apache.commons.collections.primitives.CharList;

/**
 *
 * @author serge
 */
public interface IPlay {

    
    public CharList getCombination();
    
    public void setCombination(CharList combination);
    
    public CharList getLetters_ignored();
    
    public void setLetters_ignored(CharList letters_ignored) ;
    public int getPoints();
    
    public void setPoints(int points);
    
    public CharList getLetters_matched();
    public void setLetters_matched(CharList letters_matched);
    
     public void setCombinationFromString(String combination);
     public void addPoints(int toadd);
}
