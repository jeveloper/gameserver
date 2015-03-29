/*
 * NoMatchException.java
 *
 * Created on March 22, 2007, 6:14 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.jeveloper.gameserver.core;

/**
 * if not a single match has occured
 * @author serge
 */
public class NoMatchException extends Exception{
    
    /** Creates a new instance of NoMatchException */
    public NoMatchException() {
    }
    
    public NoMatchException(String message) {
        super(message);
    }
    
    
    
}
