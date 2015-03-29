/*
 * GameStorage.java
 *
 * Created on April 13, 2007, 4:41 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.jeveloper.gameserver.enterprise.persistence;


import com.jeveloper.gameserver.beans.Draw;
import com.jeveloper.gameserver.beans.DrawList;
import com.jeveloper.gameserver.enterprise.mdb.Utility;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.NamingException;
import javax.sql.DataSource;
/**
 *
 * @author serge
 */
public class GameStorage {
    
    /** Creates a new instance of GameStorage */
    public GameStorage() {
    }
    
     public static boolean persistDraws(DrawList list, long instanceid){
        boolean rval = true;
        
        Connection conn = null;
        
        Context ctx;
        DataSource ds;
        
        //Context is most likely to be assembled with some properties
        try {
            ctx = Utility.getDBInitialContext();
            ds = (DataSource)ctx.lookup("jdbc/gameDB");            
            conn = ds.getConnection("username","password"); //IF necessary but it should be in ds file
            
        } catch (NamingException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        try{
            String sql = "INSERT INTO livedraws VALUES (?,?,?,?,?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            String formatted = "";
            
            for (Draw draw : list.getList()) {
                
                formatted = com.jeveloper.gameserver.core.GameUtility.formatMatched(draw.getLetters_matched(),draw.getServerPlay());
                stmt.addBatch();
                stmt.setLong(1,instanceid);                                
                stmt.setString(2,draw.getPlayerUsername());
                stmt.setInt(3,draw.getId());
                stmt.setString(4,String.valueOf(draw.getServerPlay().toArray()));
                stmt.setString(5,formatted);
            }
            try {                                
                int[] results = stmt.executeBatch();
                 conn.commit();
            } catch (SQLException ex) {
                conn.rollback();
                ex.printStackTrace();
            }
           
            
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            rval = false;
        }
        return rval;
    }
    
}
