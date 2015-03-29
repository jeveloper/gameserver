/*
 * Play.java
 *
 * Created on March 23, 2007, 11:30 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.jeveloper.gameserver.beans;

import com.jeveloper.gameserver.core.IPlay;
import java.io.Serializable;
import org.apache.commons.collections.primitives.ArrayCharList;
import org.apache.commons.collections.primitives.CharList;

/**
 * This class will contain a single combination for one user
 *Multiple plays are available for each user
 * @author serge
 */
public  class Play implements IPlay , Serializable{
    
  
    private CharList combination;
    //idea , have these fields in class DrawPlay which will extend Play class
    
    
    //This 2 varibles are storing everything they match within a game, in a way TOTAL ignored and TOTAL matched 
    //during a game
    //in Draw class you will find similar variables that are unique for each draw
    private CharList letters_ignored; 
    private CharList letters_matched; 
    
  
    private int points = 0; // incremental and only used for Draws

    
    //up to here
        
    /** Creates a new instance of Play */
    public Play() {
        setCombination(new ArrayCharList());
        setLetters_ignored(new ArrayCharList());
        setLetters_matched(new ArrayCharList());
    }

    public Play(int length) {
        setCombination(new ArrayCharList(length));
        setLetters_ignored(new ArrayCharList(length));
        setLetters_matched(new ArrayCharList(length));
    }
    
    
    public CharList getCombination() {
        return combination;
    }

    public void setCombination(CharList combination) {
        this.combination = combination;
    }
    
    public void setCombinationFromString(String combination){
        char[] charr = combination.toCharArray();
        for(int i=0;i<charr.length;i++){
            this.getCombination().add(charr[i]);
        }
        charr = null;
        
    }
    
    public void addLetterToIgnore(char letter){
        this.getLetters_ignored().add(letter);
    }
    public void addLettersToIgnoreList(CharList letters){
        this.getLetters_ignored().addAll(letters);
    }

    public CharList getLetters_ignored() {
        return letters_ignored;
    }

    public void setLetters_ignored(CharList letters_ignored) {
        this.letters_ignored = letters_ignored;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public CharList getLetters_matched() {
        return letters_matched;
    }

    public void setLetters_matched(CharList letters_matched) {
        this.letters_matched = letters_matched;
    }
    
    public void addPoints(int toadd){
        this.points += toadd;
    }

}
