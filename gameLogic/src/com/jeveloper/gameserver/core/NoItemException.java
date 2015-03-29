/*
 * NoItemException.java
 *
 * Created on March 21, 2007, 9:36 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.jeveloper.gameserver.core;

/**
 * An Item, whatever it might be, e.g. Player is not in a list
 * @author serge
 */
public class NoItemException extends Exception {
    
    /** Creates a new instance of NoItemException */
    public NoItemException() {
    }
    
    public NoItemException(String message){
        super(message);
    }
}
