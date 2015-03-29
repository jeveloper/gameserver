/*
 * GameUtility.java
 *
 * Created on March 21, 2007, 8:54 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.jeveloper.gameserver.core;

import java.util.List;
import org.apache.commons.collections.primitives.ArrayCharList;
import org.apache.commons.collections.primitives.CharIterator;
import org.apache.commons.collections.primitives.CharList;



/**
 *
 * @author serge
 */
public class GameUtility {

    
    
    
    
    /**
     * Creates a new instance of GameUtility
     */
    public GameUtility() {
        
    }
    
    public static int getBest(List<IPlay> lst){
        int rval = 0;
        int pts = 0;
        for(IPlay play : lst){
            pts = play.getPoints();
            if (rval < pts)
                rval = pts;
        }
        return rval;
    }
    
    
    public static String formatMatched(String matched, CharList server_play){
/*
 *this method is required for persisting in this format
 *
 *Ran this TEST :
 *Play del = new Play(7);
        del.setCombinationFromString("ABCDEFG");
 
        String formatted = GameUtility.formatMatched("DA",del.getCombination());
        print(" FORMATTED: "+ formatted);
 *
 *print the following:  FORMATTED: 1;0;0;1;0;0;0;
 *
 *completed 5-7 testing of this
 */
        String rval = "";
        
        
        for(Character ch : server_play.toArray()){
            
            if (isCharacterIn(matched,ch.toString())){
                rval += "1;";
            }else{
                rval += "0;";
            }
        }
        
        
        
        return rval;
    }
    
    public static CharList filterReoccur(CharList existing, CharList adding){
        CharList r_val = new ArrayCharList();
        CharList to_handle = new ArrayCharList(adding.size());
        to_handle.addAll(adding);
        char toread;
        for( CharIterator i = existing.iterator();i.hasNext();){
            toread = i.next();
            if (to_handle.contains(toread)){
                //if matched
                to_handle.removeElement(toread);
            }
        }
        r_val.addAll(to_handle);
        return r_val;
    }
    
    public static boolean isCharacterIn(String tosearch, String ch){
        if (tosearch.contains(ch))
            return true;
        else
            return false;
    }
    
    public static CharList getFiltered(CharList combination, CharList server_combination, CharList exclude_list, CharList letters_added) {
        
        
        CharList r_val = new ArrayCharList();
        char toread;
        
        for( CharIterator i = server_combination.iterator();i.hasNext();){
            //i.next()
            toread = i.next();
            if (combination.contains(toread)){
                
                //check if its also in the exclude list
                if (!exclude_list.contains(toread)){
                    r_val.add(toread);
                    exclude_list.add(toread);  //NOTICE: the exclude list gets changed using this METHOD
                    //proved to be effecient to work like this
                    
                }else{
                    // purely for displaying what was ignored for a second time or more
                    letters_added.add(toread);
                }
            }
        }
        
        return r_val;
    }
    
    
    //returns matched characters without filter for single combination
    public static CharList getFiltered(CharList userplay, CharList server_combination){
        CharList r_val = new ArrayCharList();
        char toread;
        
        for( CharIterator i = server_combination.iterator();i.hasNext();){
            
            toread = i.next();
            if (userplay.contains(toread)){
                r_val.add(toread);
            }
        }
        return r_val;
    }
    
    
}
