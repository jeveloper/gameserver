/*
 * StarterMDB.java
 *
 * Created on April 13, 2007, 12:27 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.jeveloper.gameserver.enterprise.mdb;

import com.jeveloper.gameserver.core.BaseGame;
import com.jeveloper.gameserver.enterprise.communication.RespondUponCompletion;
import com.jeveloper.gameserver.enterprise.persistence.GameStorage;
import javax.annotation.Resource;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.ejb.MessageDrivenContext;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

/**
 * Entity class StarterMDB
 *
 * @author serge
 */
@MessageDriven(mappedName = "jms/StarterMDB", activationConfig =  {
    @ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge"),
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
    @ActivationConfigProperty(
    propertyName = "destination", propertyValue = "queue/GameStarter")
})
public class StarterMDB implements MessageListener {
    @Resource
    private MessageDrivenContext mdc;
    /** Creates a new instance of StarterMDB */
    public StarterMDB() {
    }
    
    public void onMessage(Message message) {
        ObjectMessage msg = null;
        try {
            if (message instanceof ObjectMessage) {
                msg = (ObjectMessage) message;
                BaseGame game = null;
                try{
                    game =  (BaseGame)msg.getObject();
                    //start game
                    game.startGame();
                    //store it
                    GameStorage.persistDraws(game.getDrawList(),game.getId());
                    //send back response
                    RespondUponCompletion.sendReply(game);
                }catch(java.lang.ClassCastException ex){
                    
                }
                
                
            }
        } catch (JMSException e) {
            e.printStackTrace();
            mdc.setRollbackOnly();
        } catch (Throwable te) {
            te.printStackTrace();
        }
        
    }
    
}
