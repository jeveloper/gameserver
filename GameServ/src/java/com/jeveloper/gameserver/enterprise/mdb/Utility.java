/*
 * Utility.java
 *
 * Created on April 15, 2007, 8:09 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.jeveloper.gameserver.enterprise.mdb;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author serge
 */
public class Utility {
    
    /** Creates a new instance of Utility */
    public Utility() {
    }
    
    public static InitialContext getSenderInitialContext(){
        Properties env = new Properties();
        javax.naming.InitialContext ctx=null;
        try {
            
            env.load(ClassLoader.getSystemClassLoader().getResourceAsStream("com/jeveloper/gameserver/enterprise/mdb/jndi-sender-props.properties"));
            try {
                ctx = new javax.naming.InitialContext(env);
            } catch (NamingException ex) {
                ex.printStackTrace();
            }
            
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
        
        return ctx;
    }
    
    
    
    public static InitialContext getDBInitialContext(){
        Properties env = new Properties();
        javax.naming.InitialContext ctx=null;
        try {
            env.load(ClassLoader.getSystemClassLoader().getResourceAsStream("com/jeveloper/gameserver/enterprise/mdb/jndi-db-props.properties"));
            try {
                ctx = new javax.naming.InitialContext(env);
            } catch (NamingException ex) {
                ex.printStackTrace();
            }
            
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
        
        return ctx;
    }
    
}
