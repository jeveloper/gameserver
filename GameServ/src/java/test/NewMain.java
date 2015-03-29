/*
 * NewMain.java
 *
 * Created on April 13, 2007, 12:40 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package test;

import com.jeveloper.gameserver.CombinationGenerator;
import com.jeveloper.gameserver.beans.Play;
import com.jeveloper.gameserver.beans.Player;
import com.jeveloper.gameserver.beans.PlayerList;
import com.jeveloper.gameserver.core.BaseGame;
import com.jeveloper.gameserver.core.IPlay;
import com.jeveloper.gameserver.enterprise.mdb.Utility;
import com.jeveloper.gameserver.games.EliminationGame;
import com.jeveloper.gameserver.games.StandardGame;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.naming.Context;
import javax.naming.NamingException;

/**
 *
 * @author serge
 */
public class NewMain {
    
    /** Creates a new instance of NewMain */
    public NewMain() {
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            new NewMain().sendJMSMessageToStarterMDB("will execute a game");
        } catch (NamingException ex) {
            ex.printStackTrace();
        } catch (JMSException ex) {
            ex.printStackTrace();
        }
    }

    private void print(String msg){
        System.out.println(msg);
    }
    private Message createJMSMessageForStarterMDB(Session session, BaseGame messageData) throws JMSException {        
        javax.jms.ObjectMessage om = session.createObjectMessage();                        
        
        om.setObject(messageData);
        return om;
    }
    
    private void sendJMSMessageToStarterMDB(Object messageData) throws NamingException, JMSException {
        Context c = Utility.getSenderInitialContext();        
        ConnectionFactory cf = (ConnectionFactory) c.lookup("ConnectionFactory");
        Connection conn = null;
        Session s = null;
        
        // REMOVE THIS
        BaseGame game;
        
        game = new StandardGame();
        
        
        game.getGameType().setCombination_length(3);
        CombinationGenerator cg = new CombinationGenerator(game.getGameType().getCombination_length());
        
        //player list will be initialized on standard game
        
        Player player = new Player(1234,"Serge Bornow",game.getGameType().getMax_plays());
        IPlay play1 = new Play(game.getGameType().getCombination_length());
        play1.setCombinationFromString("ABC");
        player.addPlay(play1);
        //add another
        IPlay play11 = new Play(game.getGameType().getCombination_length());
        play11.setCombinationFromString("TFZ");
        player.addPlay(play11);
        
        
        
        Player player2 = new Player(144,"Antonin Dvorak",game.getGameType().getMax_plays());
        IPlay play2 = new Play(game.getGameType().getCombination_length());
        play2.setCombinationFromString("DSK");
        player2.addPlay(play2);
        
        Player player3 = new Player(23,"Mahler",game.getGameType().getMax_plays());
        IPlay play3 = new Play(game.getGameType().getCombination_length());
        play3.setCombinationFromString("ZYU");
        player3.addPlay(play3);
        
        
        
        
        PlayerList playerlist = new PlayerList(game.getGameType().getMax_players());
        playerlist.addPlayer(player);
        playerlist.addPlayer(player2);
        playerlist.addPlayer(player3);
        
        //IMPORTANT
        // SETTING THE playerlist to game
        game.setPlayerList(playerlist);
        
        //REMOVE
        
        
        
        try {
            conn = cf.createConnection();
            s = conn.createSession(false,s.AUTO_ACKNOWLEDGE);
            Destination destination = (Destination) c.lookup("queue/GameStarter");
            MessageProducer mp = s.createProducer(destination);
            mp.send(createJMSMessageForStarterMDB(s,game));
        } finally {
            if (s != null) {
                s.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        
        
    }
    
  
}
