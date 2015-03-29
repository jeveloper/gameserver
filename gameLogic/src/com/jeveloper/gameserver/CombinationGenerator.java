/*
 * CombinationGenerator.java
 *
 * Created on March 23, 2007, 9:43 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.jeveloper.gameserver;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.apache.commons.collections.primitives.ArrayCharList;
import org.apache.commons.collections.primitives.CharList;

/**
 *
 * @author serge
 */
public class CombinationGenerator {
    
    private  java.util.Random random;
    private   final String  alphabet = "abcdefghijklmnopqrstuvwxyz".toUpperCase();
    private int length = 0;
    
    private List<Character> current_combination;
    
    
    /** Creates a new instance of CombinationGenerator */
    public CombinationGenerator(int len) {
        setRandom(new java.util.Random(System.currentTimeMillis()));
        setLength(len);
        setCurrent_combination(new ArrayList<Character>(this.length));
        
    }
    
    private  void  generate(){
        this.current_combination.clear(); //brand new generation
        
        char selectedc;
        int randint =0;
        boolean check = true;
        String fullstr  = "";
        
        for (int i=0;i<getLength();i++){
            
            check = true;
            randint = getRandom().nextInt(26); //0 is inclusive
            
            selectedc = alphabet.charAt(randint);
            
            //dont add to the List yet we need to check uniquness
            
            fullstr += selectedc;
            
            while(check){
                
                randint = getRandom().nextInt(26); //0 is inclusive
                selectedc = alphabet.charAt(randint);
                
                if (fullstr.indexOf(selectedc) >= 0){
                    //found the same letter
                    //iterate again
                    check = true;
                }else{
                    //didnt find the same letter
                    //save and continue
                    this.current_combination.add(selectedc);
                    fullstr += selectedc;
                    check = false;
                }
            }
        }
        fullstr=null;
        
    }
    
    public Character getSingleChar(){
        this.generate();
        return this.getCurrent_combination().get(0);
    }
    
    public CharList getSorted(){
        this.generate();
        CharList rval = new ArrayCharList(this.length);
        Collections.sort(this.current_combination);
        for(Character chd : this.current_combination){
            rval.add(chd.charValue());
        }
        
        return rval;
    }

    
    public void clear(){
        this.current_combination.clear();
    }
    
    public java.util.Random getRandom() {
        return random;
    }
    
    public void setRandom(java.util.Random random) {
        this.random = random;
    }
    
    public int getLength() {
        return length;
    }
    
    public void setLength(int length) {
        this.length = length;
    }
    
    public List<Character> getCurrent_combination() {
        return current_combination;
    }
    
    public void setCurrent_combination(List<Character> current_combination) {
        this.current_combination = current_combination;
    }
}
